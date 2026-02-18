Aardvark is an extensible CLJS i18n translation system that captures & logs useful information at compile-time. The system uses a protocol oriented design to ensure users can adapt aardvark to their workflow.

This repo is still an early WIP and so this readme is still an early scratchpad for ideas.

## Scope
Start light! Just start with one implementation.

## Protocol Considerations

### Considerations at each compiler stage
#### Start of Compile-Time
* Load and parse source dictionary(s)
#### During Compile-Time (CLJ)
* Compile translate (& optionally change-locale) macro instances
  * Analyze each instance against parsed in-memory dictionary and save warning, bugs, and stats.
  * Return in-place CLJS code 
#### End of Compile-Time (CLJ)
* Log accumulated errors & stats
  * Translate logged warning & error dictionary locations from in-memory to source dictionary.
* Preprocess in-memory dictionary? (e.g., to remove dead translations)
* Convert in-memory dictionary to desired output format? (e.g., to ICU for performance?)
#### Start of Runtime (CLJS)
* Optionally prepare? (e.g., by setting up watchers)
#### During Runtime (CLJS)
* Optionally react when change-locale is triggered? (for example, by lazily loading locale dictionary)
* React per translation instance after change-locale? (or is there a less choice-constraining approach?)


### What is intrinsically coupled? What isn't?
* Generated CLJS is coupled with generated output dictionary and CLJS reactive design
* Parsing source is coupled with logging the locations of source errors.

### Can the same definition for translating source to in-memory be used for the reverse?
* if it can, should it?

## In-Memory Dictionary Design
* First thought: a flat map of keywords that are qualified to represent nesting, with the val acting as a map with translations and metadata.

### Why should a specific in-memory design contract exist at all vs user-specified definitions?

### Are interpolations allowed? How are they handled?

### What kinds of metadata are useful to store in the dictionary?

### What if locales cascade so that one locale falls back on another for some translations?

## Stray Details

### Translation strategy analysis!
* Can I enable users to automate examining tradeoffs across bundle sizes?
  * Maybe I can  measure bundle sizes given different output protocol choices! (so cool)
* Can I enable users to switch localization strategies with minimal dev time through clean decoupled protocol design!

### Callsite?
Consider how clients can use their own tr macro namespace that pulls from this library for certain benefits (like accessing custom runtime symbols from the same cljs ns)

### Configuration?
How is verbosity set if the logger is user-defined?

### Protocol
Be maximally composable. Enable radically alternative workflows through client side extension.

### Alt Workflows
What kind of state management & code should be generated?
How are alternative locale translations loaded? I need to support lazy loading and inlining, but to limit scope I'll only build one.

### Pre & Post Compilation Hooks
Needs to be done with build tools!

### Figwheel vs Shadow
Figwheel might need `recompile-dependents: true` to get Macro NS recompiled

### Terminology:
Language -> Locale

### CLJS tr
Have a cljs function that throws an exception warning not to use tr at runtime.

### Initializing
Allow clients to use their build tool to run this up front, or use a prep macro to initialize regardless of build tool

### String Manipulations
Plurals, interpolation, etc.

### Naming
* Maybe `test` should be renamed to `examples` for users looking to understand.

### Compiler Metadata
Add useful information to the compiler metadata for processing after compilation
- &form: the s-expression representing this macro call, with line and column.
- cljs.env & cljs.analyzer: ana is just the keyword ns in the env/*compiler* atom to get useful metadata

### How to Test??
* Compiler Errors seem Easy.
* But how do we test browser errors??
  * nrepl connection with an "ok" signal?

## Useful Examples

### Shadow

### Fighweel

### Reagant

### Reframe?

### DOM?

### Inlined Localizations
* The simple base case
* keywords for localizations & strings for quick mockup

### Lazily Loaded Localizations
* Strip comments from source dictionary into dictionaries
* Convert from nested to minified flat keys for perf & bundle size
* Strip dead translations out of final dictionary

### ICU Localizations using Satakieli
* Input dictionary is human readable source dictionary, output is ICU.

### String First translations with mock external translation service
* Very different paradigm! Is it transferable?

### Are there any Accessibility considerations for demonstration?
