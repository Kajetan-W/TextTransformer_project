# New Product Backlog Items (Business Value)

**1. Strip HTML Tags**
- **User Story**: As a user, I want to strip HTML tags from the text, so that I can process or read the pure plain text content without markup.
- **Acceptance Criteria**: 
  - Removes all valid HTML tags (e.g. `<b>`, `</div>`, `<script>`).
  - Leaves the inner text content intact.
  - Handled via a `strip-html` transformation in the API.

**2. Censor Profanity**
- **User Story**: As a user, I want to censor specific profanity words in the text, so that the output is family-friendly and safe for work.
- **Acceptance Criteria**: 
  - Replaces a predefined list of profanities with asterisks (e.g. `****`).
  - Case-insensitive matching.
  - Handled via a `censor` transformation in the API.

**3. Markdown to HTML Converter**
- **User Story**: As a user, I want to convert basic Markdown syntax into HTML, so that the text can be correctly rendered on web pages.
- **Acceptance Criteria**: 
  - Converts `**bold**`, `*italic*`, and `[link](url)` into their respective HTML tags.
  - Leaves non-markdown text unchanged.
  - Handled via a `markdown2html` transformation.

**4. Text Summarizer (Preview)**
- **User Story**: As a user, I want to extract the first sentence or up to 100 characters of a long text, so that I can display a preview summary.
- **Acceptance Criteria**: 
  - Truncates the text after the first period or 100 characters (whichever comes first).
  - Appends `...` if truncated.
  - Handled via a `preview` transformation.

**5. Text Statistics Appender**
- **User Story**: As a user, I want to append word count and character count to the end of the text, so that I can see the statistics of my document.
- **Acceptance Criteria**: 
  - Appends ` [Stats: X words, Y chars]` at the end of the resulting string.
  - Accurately counts words and characters (excluding the appended text itself).
  - Handled via a `stats` transformation.
