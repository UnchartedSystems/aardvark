Aardvark is an extensible CLJS i18n translation system that captures & logs useful information at compile-time. The system uses a protocol oriented design to ensure users can adapt aardvark to their workflow.

This repo is still an early WIP and so this readme is still an early scratchpad for ideas.

## Scope
Start light! Just start with one implementation.

## Protocol Considerations

### Considerations at each compiler stage
#### Start of Compile-Time
* Load and parse source dictionary(s)
#### During Compile-Time (CLJ)
* Compile `translate` (& optionally `change-locale`) macro instances
  * Analyze each instance against parsed in-memory dictionary and save instance metadata (can be warnings, bugs, stats, etc).
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
* Loading & parsing source dictionary is coupled with logging specific source dictionary locations associated with errors 
* Loading is *not* necessarily coupled with parsing source dictionaries
* Logging instance metadata is coupled with logging accumulated errors

### Can the same definition for translating source to in-memory be used for the reverse?
* if it can, should it? I need to do more research here.

## Protocol Design

### Parsing
#### Load Source Dictionary(s)
#### Parse Source Dictionary(s) & Convert to In-Memory Dictionary
* Why not condense Loading & Parsing? Changing source locations shouldn't require modifying preexisting extensions for parsing & converting common dictionary formats.
#### Logging Source Dictionary(s) Warnings/Errors
* Requires mapping between source dictionary(s) and in-memory dictionary. 
### Logging
#### Store Instance Metadata
#### Log Diagnostics From Metadata
### Output
#### Generate Instance CLJS
#### Convert In-Memory to Output Dictionary
* *If applicable!* phrases might be inlined.
#### Optionally Prepare CLJS system
#### (Not In Protocol) Hand-Rolled (?) Reactive CLJS

## In-Memory Dictionary Design
* First thought: a flat map of keywords that are qualified to represent nesting, with the val acting as a map with translations and metadata.

### Why should a specific in-memory design contract exist at all vs user-specified definitions?

### Are interpolations allowed? How are they handled?

### What kinds of metadata are useful to store in the dictionary?

### What if locales cascade so that one locale falls back on another for some translations?

### What if only a part of a locale should be bundled given the domain a web-app user sees?
*  No problem, developers can use a first fragment of the qualified keyword namespace within the in-memory dictionary to extend the `outpout` protocol to seperate domain dictionaries.

## Potential Limitations

### Will developers be able to easily configure their build tools to output different builds with inlined translations for each served locale! I should look into this! This might be more a limitation of build tools then my library.
* shadow-cljs seems capable of doing this with aardvark! I'm not sure what use-cases this approach developers would find this be worth the squeeze for given the trivial perf cost of a key look-up compared to the overall perf overhead cljs itself. Consider including an example anyway... maybe this is relevant if developers want to try using aardvark with squint?

### Can aardvark work with Squint?
* Not sure yet, if it is, it might require a fork of aardvark! Squint only works with .cljc & .cljs macros, and probably can't use the cljs.analzyer. There is a lot to consider to make this work. Even if I can get this working, I'm still not sure it can support different builds with inlined translations per locale (if that's possible, it might require an aardvark fork).
  
## Control order of compile-time logging until after all tr & change-locale instances?
* Place a logging macro at the end of the initial namespace?
* Use 'eval' or ''

## Stray Details

### Translation strategy analysis!
* Can I enable users to automate examining tradeoffs across bundle sizes?
  * Maybe I can  measure bundle sizes given different output protocol choices! (so cool)
* Can I enable users to switch localization strategies with minimal dev time through clean decoupled protocol design!

### Callsite?
Consider how developers can use their own macro namespace that can pull from this library and include an outer implemention of tr for certain benefits (like the small bonus of accessing custom runtime symbols from the equivelant cljs ns as their clj tr macro without an additional namespace requirement)

### Configuration?
Is the logger extensible for developers?

### Protocol
Be maximally composable. Enable radically different trade-offs through developer extension.

### Alt Workflows
What kind of state management & code should be generated in default workflows?
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

### Inlined localizations map.
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
