# naco - Spring Legacy CRUD + Member Auth (Session) + Interceptor

## What this project covers ("Topic Guide" checklist)

### ✅ Signup (register)
- Stores a new user in DB
- Password is hashed using **BCrypt** (`spring-security-crypto`)

### ✅ Login
- Validates username/password (BCrypt matches)
- Auth state is kept in **HttpSession** (`LOGIN_USER`)

### ✅ Logout
- `session.invalidate()`

### ✅ Authorization (Access Control)
- **LoginInterceptor** blocks protected URLs before Controller
- **AdminInterceptor** blocks `/admin/**` unless `role == ADMIN`
- UX: when blocked, saves intended URL and redirects back after login

### ✅ Filter vs Interceptor demo (for better scoring)
- `CharacterEncodingFilter` and `HiddenHttpMethodFilter` are registered in `web.xml`
- `RequestLogInterceptor` demonstrates interceptor lifecycle (timing + status)

## URLs

### Public
- `/` (home)
- `/login`
- `/register`

### Protected (Login required)
- `/tasks/**`

### Admin only
- `/admin/users`

## Database

Run `src/main/resources/schema.sql` in your MariaDB.

Then set DB connection in:
- `src/main/webapp/WEB-INF/spring/root-context.xml`

## Notes

- If you want an ADMIN account quickly, register a user and then change role on `/admin/users` (needs one existing admin). 
  Alternatively, manually update DB: `UPDATE users SET role='ADMIN' WHERE username='...';`.
