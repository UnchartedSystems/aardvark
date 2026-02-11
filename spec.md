# High Level

Aardvark is an extensible translation system that captures & logs useful information at clojurescript compile-time. The system uses protocols to enable users to adapt aardvark to their own workflow.

## CLJ Compile Time
* Parse source dictionary
* Compile tr & locale:
  * save bugs & stats
  * generate cljs code
## Compile symbols
* log accumulated bugs & stats
* Process output dictionary?
## CLJS Runtime
At runtime, the process is:
* manage state & reactivity
* load & use translations inlined or lazy?


# Scope
Start light! Just start with one implementation.

# Details
## Protocol
Be maximally composable. Enable radically alternative workflows through client side extension.

### Alt Workflows
What kind of state management & code should be generated?
How are alternative locale translations loaded? I need to support lazy loading and inlining, but to limit scope I'll only build one.

## Pre & Post Compilation Hooks
Needs to be done with build tools!

## Terminology:
Language -> Locale

## CLJS tr
Have a cljs function that throws an exception warning not to use tr at runtime.

## Initializing
Allow clients to use their build tool to run this up front, or use a prep macro to initialize regardless of build tool

## String Manipulations
Plurals, interpolation, etc.

## Compiler Metadata
Add useful information to the compiler metadata for processing after compilation

Use:
- &form: the s-expression representing this macro call, with line and column.
- cljs.env & cljs.analyzer: ana is just the keyword ns in the env/*compiler* atom to get useful metadata
