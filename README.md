# narwhal-server

'''based on spring-cloud-gateway, not spring-cloud-gateway'''
this api-gateway is based on spring-cloud-gateway, but not spring-cloud-gateway, it is a simple api-gateway, it is easy
to use, and it is easy to extend.
its api is managed by three level , app -> api group / plain api <-> backend api.

### client perspective
consumer app :
        app id
        app access verify
        predicates
        global filters
        api groups

api group:
        api group id
        api group name
        api group description
        predicates
        group filters
        apis
api:
        api id
        api name
        api description
        predicates
        api filters
        backend api

### backend perspective
backend project:
        backend api id
        backend api name
        backend api description
        project filters

backend api:
        backend api id
        backend api name
        backend api description
        backend api url
        backend api method
        backend api headers
        backend api params
        backend api body
        backend api response

client api vs backend api is 1:1
