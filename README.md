# shunting-yard-parser

A personal coding project to implement the Shunting-yard algorithm in java from scratch that parses and evaluates infix expressions using raw arrays for stacks instead of using `java.util.Stack`. It works by first converting the infix to postfix then evaluating it.

## Core Features
* **Supported Operators:** Processes standard arithmetic operations (`+`, `-`, `*`, `/`) alongside parentheses (`(`, `)`).
* **Multi-Digit Support:** Tracks string indices dynamically to extract multi-digit numbers.

## Example Usage
```
Expression: 10/(1+2)
Result: 3.333333
```

## Critical Constraint
This parser does **not** validate syntax. Passing malformed expressions (e.g., mismatched parentheses or broken operators) will output an incorrect result or throw a runtime exception.
