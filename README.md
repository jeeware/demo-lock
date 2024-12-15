docker run -d --name mongo_replicaset -v ~/data/db:/data/db -p 27017:27017 mongo --replSet rs0
docker exec -it mongo_replicaset mongo
rs.initiate({ _id: "rs0", members: [ { _id: 0, host : "localhost:27017" } ] } )
rs.conf() // pour verifier