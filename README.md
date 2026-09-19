# Shahrukh Auto Care - New Version

Separate repository/project from the original website.

Features:
- Customer enquiry form
- Mandatory 10-digit Indian mobile number
- PostgreSQL storage
- Owner-only admin dashboard
- Admin login using Render environment variables
- Browser notification for new enquiries while admin dashboard is open
- Auto-refresh every 10 seconds
- Call customer from admin
- Delete enquiry

Customer URL: /
Admin URL: /admin.html

Set Render variables:
ADMIN_USERNAME = your chosen username
ADMIN_PASSWORD = a strong private password

Do not put the real password in GitHub.

The current free implementation does not use WhatsApp/SMS APIs. Browser notifications require the owner to have opened the admin dashboard and granted notification permission.

The photo filename is stored; the actual image file is not uploaded in this version.
