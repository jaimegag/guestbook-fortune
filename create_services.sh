cf cs p-config-server standard config-server -c '{"git":{"uri":"https://github.com/jaimegag/config-repo"}}'
cf cs p-service-registry standard service-registry
cf cs p-circuit-breaker-dashboard standard circuit-breaker
cf cs p-mysql 100mb-dev gbfortune-db
