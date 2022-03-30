extern crate jvm_rs;

use std::{ffi::c_void, fs, os::raw::c_int, ptr::null_mut};
use std::env::temp_dir;
use std::ffi::{CStr, CString};
use std::os::raw::{c_char, c_uchar};
use std::path::Path;
use jvm_rs::{
    jni::{
        JavaVM,
        jboolean,
        jclass,
        jint,
        JNI_OK,
        JNI_VERSION_1_1,
        JNIEnv,
        jstring,
    },
    jvmti::{
        JVMTI_VERSION_1_1,
        jvmtiEnv,
        jvmtiError_JVMTI_ERROR_NONE,
    },
};
use jvm_rs::jni::JNINativeMethod;

static mut JVMTI: *mut jvmtiEnv = null_mut();

#[no_mangle]
pub unsafe extern "system" fn JNI_OnLoad(_vm: *mut JavaVM, _reserved: &mut c_void) -> c_int {
    let mut ptr: *mut c_void = null_mut();
    let mut result = (*(*_vm)).GetEnv.unwrap()(_vm, &mut ptr, JVMTI_VERSION_1_1 as jint);
    if result == JNI_OK as jint {
        JVMTI = ptr.cast::<jvmtiEnv>();

        let mut ver: jint = -1;
        let error = (*(*JVMTI)).GetVersionNumber.unwrap()(JVMTI, &mut ver);
        if error != jvmtiError_JVMTI_ERROR_NONE {
            println!("[libbootstrap] Something has gone horribly wrong. @ GetVersionNumber, ver/error: {}/{}", ver, error);
            return -1;
        }
    }

    result = (*(*_vm)).GetEnv.unwrap()(_vm, &mut ptr, JNI_VERSION_1_1 as jint);
    if result == JNI_OK as jint {
        let jni = ptr.cast::<JNIEnv>();

        // Load bootstrap into the VM
        let path_file = temp_dir().join(Path::new(".atlaspath"));
        let target_path = fs::read_to_string(path_file)
            .expect("Couldn't read .atlaspath file.");

        let path = CString::new(target_path)
            .expect("Couldn't parse path_file to C string.");

        // TODO: system or boot classloader?
        (*(*JVMTI)).AddToSystemClassLoaderSearch.unwrap()(JVMTI, path.as_ptr());

        // Load the runtime class
        let name = CString::new("fr/stardustenterprises/atlas/framework/bootstrap/RuntimeEntrypoint").unwrap();
        let clazz = (*(*jni)).FindClass.unwrap()(jni, name.as_ptr());

        // Define its native method
        let mut m_name = CString::new("appendToClassLoader0").unwrap();
        let mut m_signature = CString::new("(Ljava/lang/String;)V").unwrap();

        let methods = JNINativeMethod {
            name: m_name.as_ptr() as *mut c_char,
            signature: m_signature.as_ptr() as *mut c_char,
            fnPtr: &mut Java_appendToClassLoader0 as *mut c_void,
        };
        (*(*jni)).RegisterNatives.unwrap()(
            jni,
            clazz,
            &methods,
            1 as jint,
        );

        m_name = CString::new("init").unwrap();
        m_signature = CString::new("()V").unwrap();

        // Initialize
        let method_id = (*(*jni)).GetStaticMethodID.unwrap()(
            jni,
            clazz,
            m_name.as_ptr(),
            m_signature.as_ptr(),
        );
        (*(*jni)).CallStaticVoidMethod.unwrap()(
            jni,
            clazz,
            method_id,
        );
    }

    JNI_VERSION_1_1 as i32
}

#[no_mangle]
pub unsafe extern "system" fn Java_appendToClassLoader0(
    env: *mut JNIEnv,
    _class: jclass,
    file_path: jstring,
) {
    let c_str = (*(*env)).GetStringUTFChars.unwrap()(env, file_path, &mut 0);
    (*(*JVMTI)).AddToBootstrapClassLoaderSearch.unwrap()(JVMTI, c_str);
}

// Library Injection

#[cfg(windows)]
#[no_mangle]
pub extern "system" fn DllMain(
    _: *const u8,
    _: u32,
    _: *const u8,
) -> u32 {
    1
}

#[cfg(unix)]
#[no_mangle]
pub extern "C" fn constructor() {

}
