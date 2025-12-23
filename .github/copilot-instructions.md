# Copilot / AI Agent Instructions for Group7_Retronovaa

Purpose: Short, actionable guidance to help an AI coding agent make useful, low-risk changes to this Java Swing + MySQL application quickly.

---

## Quick summary (big picture) ✅
- Desktop Java application built with NetBeans (Java Swing UI). Main entry: `group7_retronovaa.Group7_Retronovaa` (see `src/group7_retronovaa/Group7_Retronovaa.java`).
- Architecture: MVC-style split: `view/` (NetBeans `.form` + `.java` UI classes), `Controller/` (controllers attach listeners and coordinate views/DAOs), `dao/` (direct DB access), `model/` (POJOs), `utils/` (Email, OTP helpers), `database/` (DB connection helper).
- No automated tests in `test/` (empty). Build system: Ant + NetBeans project files (`build.xml`, `nbproject/`).

## How to build & run 🛠️
- Recommended: Open the project in NetBeans and Run (uses NetBeans project settings).
- CLI (Ant):
  - `ant clean` — cleans build artifacts
  - `ant` or `ant jar` — build and create `dist/Group7_Retronovaa.jar`
  - `ant run` — run (if configured by NetBeans targets)
- Main class: `group7_retronovaa.Group7_Retronovaa` (used by NetBeans `main.class` property).

## Database & external integrations 🔌
- Uses MySQL on `localhost:3306` with database name `retronova`.
  - Default connection in code: `username=root`, `password=admin123` (see `src/database/MySqlConnection.java`).
  - Expected `users` table columns (inferred from DAO SQL): `fullname`, `username`, `email`, `password` (see `src/dao/UserDao.java`).
- JDBC driver: `lib/mysql-connector-j-8.0.33.jar` is present; ensure it's on the classpath.
- Email: `utils/EmailService.java` uses SMTP (`smtp.gmail.com:587`) and stores a placeholder `EMAIL_USERNAME` / `EMAIL_PASSWORD` in the source. The project uses Jakarta/JavaMail jars referenced in `nbproject/project.properties`; ensure mail jars are available or update project properties.

## Project-specific conventions & patterns 📚
- UI: NetBeans GUI forms (`*.form` + generated `*.java`). **Edit `.form` files using NetBeans UI builder** to avoid breaking generated code.
- Controllers instantiate views and attach listeners via view-specific methods (e.g., `SignUp.AddaddUserListener(...)` in `UserController`). Follow existing patterns when adding new controller/view behavior.
- DAOs use direct SQL strings and `MySqlConnection` for `openConnection()` / `executeUpdate()` / `runQuery()`. Typical pattern: open connection, use `PreparedStatement`, finally `closeConnection()`.
- Error handling is minimal (prints stack traces or messages); tests are absent — prefer small, locally verifiable changes.

## Known, discoverable caveats & hotspots ⚠️
- Credentials and secrets are in repository code (DB creds, email username placeholders). Be careful when editing or exposing them. Prefer adding environment-variable support (if making changes).
- Password handling appears to be plain text (e.g., `UserDao.signUp` stores `password` as-is). This is a factual observation (not a prescription) and may affect scope of changes.
- Some SQL/logical issues exist (e.g., `LoginDao` query uses `WHERE username = ? OR password = ?`), so be conservative with auto-fixes — prefer proposing fixes and tests rather than mass replacements.

## How an AI agent can contribute safely ✅
- Small, focused PRs that are easy to test locally: fix a single logic bug, add input validation, or parameterize credentials (e.g., read DB credentials from env vars while preserving current defaults).
- When modifying UI code, update the `.form` via NetBeans (or only change the companion `*.java` when sure it’s not generated code).
- Add or update a minimal manual test or a small validation script for any behavioral change (since there are no unit tests).

## Files to read first (important examples) 🔍
- `src/group7_retronovaa/Group7_Retronovaa.java` — program entry and initial DB check
- `src/database/MySqlConnection.java` — how DB is opened/closed and SQL executed
- `src/dao/UserDao.java`, `src/dao/LoginDao.java` — typical DAO patterns and table expectations
- `src/utils/EmailService.java`, `src/utils/OTPService.java` — integration details for email/OTP flows
- `nbproject/project.properties` — classpath and external JAR references (jakarta.mail, mysql connector)

---

If anything here is incorrect, incomplete, or you want me to add a short example snippet for a common task (e.g., "how to add an env var for DB creds"), tell me which area to expand and I'll iterate. Thanks!