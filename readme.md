
## Steps to Run the OAuth example

Start the Authorization server on port 8080

```
cd authserver
mvn spring-boot:run
```

Start the resource server on port 9090. The resource server in our case has the REST API we would like to access using a token provided by the authorization server.

```
cd resourceserver
mvn spring-boot:run
```

Switch to another terminal window so we can simulate a client invoking the REST API. The client has to first invoke the authorization server with client credentials (grant type) in our case. Once authenticated and authorized the authorization server will return a token. In this case the token uses the JWT token format. Refer to [https://jwt.io](https://jwt.io) for details on JWT. The jwt.io site also provides a debugger to view the contents of the token itself. Definitely use that to familiarize yourself with the three sections in the payload.

```
curl clientid1:secret1@localhost:8080/auth/oauth/token -d grant_type=client_credentials

you will get something like...
{"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTA3ODE0NDYzLCJqdGkiOiIxMTZkNmE3Ny05NDRiLTQxM2MtOTRiYi1hNjk5NzgwZmZhOGQiLCJjbGllbnRfaWQiOiJjbGllbnRpZDEifQ.fAfCVywxxU6iaEA5btNRFpoGy4XxnWaHjuALRvYJ-GO0LgVu4FJhm8kbvQ5sbS4Gapx_ZIXngne2R7UcnXSkb45JirZrzIWe_VHjAxQhky3UF4wlGXxpNhQar98vOCt_v-uufzjdJUD8kBdRVLUleFGZzXhtvbE7PsUO96KOK5YRCFIOiT-8f_PESug9L5MW5DrWzl1EmfYF9YOPr_43ezuhS3YMZFthQpEc5nqPyVoCRvJXDjipshnTUpHMaHR0Ujvl5m3eTIahvvc38K104MlxhxCLJosBtp2KTYOQMfBpE4qMxM2grn_OBAG093ozOl8bTfUiWsnSQNCzBb39sg","token_type":"bearer","expires_in":599,"scope":"read write","jti":"116d6a77-944b-413c-94bb-a699780ffa8d"}
```

We get the access token which must be used on the invocation to the REST API you are interested in. 


```
curl -H "Authorization: Bearer <tokenhere>" localhost:9090
If all goes well you should get a valid response
{"message":"Hello from OAuth enabled endpoint."}
```