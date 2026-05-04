# TextTransformer project
The application is designed for people working with text data. It allows users to transform text by applying selected operations, such as changing letter case, reversing text, eliminating duplicated words, or escaping special characters. The application will be available through a remote REST API and GUI, thanks to which it can be integrated with existing tools.

## Project Description

The main goal of the project is to create an application that receives text input from the user and returns the transformed result.

Example input:

```text
Mirek
```

Example transformations:

```text
upper
inverse
```

Example result:

```text
KERIM
```

The application should support applying one or more transformations to the same input text.

## Main Features

Features of the application include:

- transforming text through a REST API
- changing text case:
  - upper case
  - lower case
  - capitalize
- reversing text
- removing duplicated words
- escaping special characters for LaTeX
- replacing selected words with acronyms
- expanding acronyms into full words
- providing a GUI in later stages of the project