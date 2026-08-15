# Project notes

## Environment fix (2026-08-15, after Windows 10 → Ubuntu 26.04 migration)

### JDK
- The project requires **Java 19** (`maven.compiler.source/target=19` in the root `pom.xml`).
- The system default `java` on Ubuntu was OpenJDK **8**, which broke Maven/Eclipse with
  `Fatal error compiling: invalid target release: 19`.
- Fixed by setting sdkman default: `sdk default java temurin-19`
  (installed at `~/.sdkman/candidates/java/temurin-19`).
- Build with:
  ```
  mvn clean install -DskipTests
  ```
  If `java` resolves to an old version, prepend JAVA_HOME:
  ```
  JAVA_HOME=~/.sdkman/candidates/java/temurin-19 mvn clean install -DskipTests
  ```

### Eclipse
- Snap Eclipse runs on Temurin 21; configure the project to use JDK 19:
  Window → Preferences → Java → Installed JREs → Add Standard VM →
  `~/.sdkman/candidates/java/temurin-19`, then set the project's compiler level to 19.

### Eclipse XML validation errors in pom.xml
- Errors `cvc-elt.1.a: Cannot find the declaration of element 'project'` +
  `Downloading external resources is disabled` on `pom.xml` come from the XML
  Language Server (Lemminx / org.eclipse.wildwebdeveloper.xml), which refuses to
  fetch the Maven XSD by default (security measure).
- Fixed by enabling download of external resources. Either tick
  Window → Preferences → XML → "Download external resources like referenced
  DTD, XSD", or set the workspace preference
  `xml.preferences.downloadExternalResources.enabled=true` in
  `<workspace>/.metadata/.plugins/org.eclipse.core.runtime/.settings/org.eclipse.wildwebdeveloper.xml.prefs`,
  then fully restart Eclipse (the running instance may overwrite the file).
- The XSD in question is the official Maven POM schema
  `https://maven.apache.org/xsd/maven-4.0.0.xsd` — safe to download, only used
  for editor validation/autocompletion, not for the build.
- Note: `engine-graphics/pom.xml` (locally modified submodule) still uses the
  plain `http://` URL for it.

## Dead code removed
- `engine-graphics/src/main/java/com/digiturtle/` was legacy **LWJGL 2** code
  (ambiguous static imports `ARBBufferObject` vs `ARBVertexBufferObject`,
  incompatible with LWJGL 3.3). Nothing referenced it; it was untracked in git and
  was deleted locally. Do NOT re-add it.

## Project state restore (2026-08-15) — broken main-menu text
- Root cause: local repo was stale (HEAD `aeb0628`, Apr 2024). The working copy
  was an old Windows snapshot (CRLF line endings) with half-finished local tweaks.
- Restored to the fixed state: parent `origin/gui-refactor` (`b3529d3` "Add font
  loading to main menu") + submodules on their remote `release` branches:
  `engine=fb35aab`, `engine-common=d3ff894`, `engine-graphics=fb790a4`,
  `engine-world=983a123`. Old unstaged changes were discarded by design.
- The committed state did NOT compile; local fixes were required (still
  UNCOMMITTED in the submodules — do not reset them away):
  - engine-common: added `com/xamlo/core/engine/graphics/font/CharacterData.java`
    (moved here from engine-graphics; it is needed by `engine.api.font.IGlyphPage`).
  - engine-graphics: removed `font/CharacterData.java`; `FontResource` now
    delegates to `UnicodeGlyphFont`; `UnicodeGlyphFont` implements
    `getFontAtlas()`; `FontAtlas.createCharMesh` uses `IGlyphPage`;
    `TextElementRenderer` uses `ApplicationFont` (the `FontKey` class never
    existed — leftover of an unfinished refactor).
- Verify: `mvn clean install -DskipTests` → BUILD SUCCESS; run game with
  `JAVA_HOME=~/.sdkman/candidates/java/temurin-19 java -jar game/target/game-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
  and check for `render glyph:` log lines.
- Running the game regenerates `font_atlas_0.png` in the working dir; it is a
  runtime artifact and can be deleted.
