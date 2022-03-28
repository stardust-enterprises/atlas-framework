# Atlas Framework
___

[![Build][badge-github-ci]][project-gradle-ci]
[![Maven Central][badge-mvnc]][project-mvnc]

**Atlas** is a **next-gen** modding toolchain designed for 
intercompatibility and efficiency, and we're not taking this lightly.

The **Atlas Framework** is the core of the platform, allowing for loading and 
management of mods, as well as **[linking them](#linkermodule-linker)** to the program's 
source code, via the use of [mapping classes](#mapping-classes).

# Concepts
___

### Mapping classes

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Duis ultricies lacus sed turpis
tincidunt. Ultrices eros in cursus turpis massa.

### Linked Proxy classes

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Duis ultricies lacus sed turpis
tincidunt. Ultrices eros in cursus turpis massa.

### Facade Proxy classes

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Duis ultricies lacus sed turpis
tincidunt. Ultrices eros in cursus turpis massa.

# Modules
___

The **Atlas Framework** is divided into many (sub)modules for ease of 
code readability and maintainability.

Here is a quick description for each one:

### [annotations][module-annotations]

The annotations required for both the Mapping declarations and the generated
linked proxies.

### [bootstrap][module-bootstrap]

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Duis ultricies lacus sed turpis
tincidunt. Ultrices eros in cursus turpis massa.

### [bootstrap-native][module-bootstrap-native]

Native module injected into the target process to append the framework's
[bootstrap](#bootstrapmodule-bootstrap) and start the runtime injection process.

### [engine][module-engine]

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Duis ultricies lacus sed turpis 
tincidunt. Ultrices eros in cursus turpis massa.

### [linker][module-linker]

Module in charge of analyzing the required [mapping classes](#mapping-classes),
and creating runtime [linked proxy classes](#linked-proxy-classes) based on those.

### [loader][module-loader]

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Duis ultricies lacus sed turpis
tincidunt. Ultrices eros in cursus turpis massa.

# Contributing
___

You can contribute by [forking the repository][fork], making your changes and 
[creating a new pull request][new-pr] describing what you changed, why and how.

# Licensing
___

This project is under the [ISC license][project-license].

<!-- Links -->

[jvm]: https://adoptium.net "adoptium website"

[kotlin]: https://kotlinlang.org "kotlin website"

[rust]: https://rust-lang.org "rust website"

[mvnc]: https://repo1.maven.org/maven2/ "maven central website"

<!-- Module Links -->

[module-annotations]: https://github.com/stardust-enterprises/atlas-framework/tree/trunk/annotations "annotations module link"

[module-bootstrap]: https://github.com/stardust-enterprises/atlas-framework/tree/trunk/bootstrap "bootstrap module link"

[module-bootstrap-native]: https://github.com/stardust-enterprises/atlas-framework/tree/trunk/bootstrap-native "bootstrap-native module link"

[module-engine]: https://github.com/stardust-enterprises/atlas-framework/tree/trunk/engine "engine module link"

[module-linker]: https://github.com/stardust-enterprises/atlas-framework/tree/trunk/linker "linker module link"

[module-loader]: https://github.com/stardust-enterprises/atlas-framework/tree/trunk/loader "loader module link"

<!-- Project Links -->

[project-url]: https://github.com/stardust-enterprises/atlas-framework "project github repository"

[fork]: https://github.com/stardust-enterprises/atlas-framework/fork "fork this repository"

[new-pr]: https://github.com/stardust-enterprises/atlas-framework/pulls/new "create a new pull request"

[new-issue]: https://github.com/stardust-enterprises/atlas-framework/issues/new "create a new issue"

[project-mvnc]: https://maven-badges.herokuapp.com/maven-central/fr.stardustenterprises/atlas-framework "maven central repository"

[project-gradle-ci]: https://github.com/stardust-enterprises/atlas-framework/actions/workflows/gradle-ci.yml "gradle ci workflow"

[project-license]: https://github.com/stardust-enterprises/atlas-framework/blob/trunk/LICENSE "LICENSE source file"

<!-- Badges -->

[badge-mvnc]: https://maven-badges.herokuapp.com/maven-central/fr.stardustenterprises/atlas-framework/badge.svg "maven central badge"

[badge-github-ci]: https://github.com/stardust-enterprises/atlas-framework/actions/workflows/build.yml/badge.svg?branch=trunk "github actions badge"
