# API design

| Resource | Verb and path | Success | Failure |
|---|---|---:|---|
| Latest rates | `GET /api/rates` | 200 | database error |
| One latest rate | `GET /api/rates/{base}/{quote}` | 200 | unknown pair: 404 |
| Conversion | `POST /api/conversions` | 201 | invalid payload: 400; unknown pair: 404 |
