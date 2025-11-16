# 🛡️ Insurance Claim Approval System

> Full-stack **Insurance Claim Approval Platform** built with **Spring Boot**, **Angular**, and **MySQL**. It enables Clients to file claims and track them, and Agents to review and update statuses — secured with **JWT** and clean **role-based access control (RBAC)** via `RoleMaster`.

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-21-red?logo=openjdk&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=spring&logoColor=white">
  <img alt="Angular" src="https://img.shields.io/badge/Angular-20-DD0031?logo=angular&logoColor=white">
  <img alt="MySQL" src="https://img.shields.io/badge/MySQL-8.x-005C84?logo=mysql&logoColor=white">
  <img alt="Build" src="https://img.shields.io/badge/Build-Maven-blue?logo=apache-maven&logoColor=white">
  <img alt="Auth" src="https://img.shields.io/badge/Auth-JWT-black">
</p>

---

## ✨ Highlights

- 👤 **Two roles**: `CLIENT`, `AGENT` (pre-seeded Agents, self-service Client signup)
- 🧾 **Claims lifecycle**: create → review → update status → view history
- 🧑‍🤝‍🧑 **Dependents** support (with relationship types)
- 🏥 **Hospital directory** & network catalogs
- 🔐 **JWT Security** with session logs/audits
- 🧱 **Normalized schema**: master tables for Policy, Status, Claim Type, Relation, Role
- 📦 Clean **Controller → Service → Repository** layering

---

## 🧭 Project Map

```
insuranceAssist/
 ├─ server/
 |  ├─ config/                # Custom configuration files
 │  ├─ controller/            # REST controllers
 │  ├─ service/               # Business logic (incl. JwtService, UserDetailsServiceImpl)
 │  ├─ entity/                # UserMaster, RoleMaster, Policy/Claim/Hospital/... Masters
 │  ├─ repository/            # Spring Data JPA repos
 │  ├─ dto/                   # Data Transfer Objects for Service to Controller communication
 |  ├─ exception/             # Custom exception classes
 │  └─ application.properties # MySQL, JPA, JWT secrets
 └─ client/ (Angular)       # Client UI
```

---

## 📚 REST API

- **Auth:** `/register`, `/login`
- **Profile:** `/private/profile/{userId}`, `/private/profile/update/{userId}`
- **Policy:** `/private/policy/update/{policyId}`, `/private/policy/{policyId}`
- **Claims:** `/private/claim/create`, `/private/claim/update/{claimId}`, `/private/claim/history/{userId}`
- **Dependents:** `/private/dependent`, `/private/dependent/{id}`, `/private/dependent/user/{userId}`
- **Hospitals:** `/public/hospital/{id}`, `/public/hospitals`

---

## ⚙️ Local Setup

### MySQL
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/insurancedb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=JWT_SECRET
jwt.expiration=3600000
```

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
ng serve
```

---

## 🔮 Future Scope

- Admin dashboard for master data & audits  
- Email/SMS notifications for claim status  
- Payment gateway integration  
- Fraud detection & rule engine  
- Cloud deployment (AWS/GCP/Azure)  

---

## 💼 Business Impact

- ⏱️ **Faster claim processing**  
- 🔒 **Fraud prevention with RBAC + JWT**  
- 📊 **Transparency & audit logs**  
- 🌍 **Scalable, enterprise-ready**  

