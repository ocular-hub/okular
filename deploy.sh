docker build -t ocular2020/multi-client:latest -t ocular2020/multi-client:$SHA -f ./client/Dockerfile ./client
docker build -t ocular2020/multi-server:latest -t ocular2020/multi-server:$SHA -f ./server/Dockerfile ./server
docker build -t ocular2020/multi-worker:latest -t ocular2020/multi-worker:$SHA -f ./worker/Dockerfile ./worker

docker build -t ocular2020/postgres-ddl:latest -t ocular2020/postgres-ddl:$SHA -f ./pgdb/Dockerfile ./pdgb
docker build -t ocular2020/openliberty-openjdk11:latest -t ocular2020/openliberty-openjdk11:$SHA -f ./jserv/openliberty/Dockerfile ./jserv/openliberty
cd ./jserv
mvn clean package
cd -
docker build -t ocular2020/jserv:latest -t ocular2020/jserv:$SHA -f ./jserv/Dockerfile ./jserv

docker push ocular2020/multi-client:latest
docker push ocular2020/multi-server:latest
docker push ocular2020/multi-worker:latest
docker push ocular2020/postgres-ddl:latest
docker push ocular2020/openliberty-openjdk11:latest
docker push ocular2020/jserv:latest


docker push ocular2020/multi-client:$SHA
docker push ocular2020/multi-server:$SHA
docker push ocular2020/multi-worker:$SHA
docker push ocular2020/postges-ddl:$SHA
docker push ocular2020/openliberty-openjdk11:$SHA
docker push ocular2020/jserv:$SHA

kubectl apply -f k8s
# server is the container name in the deployment config, check the containers section
# server-deployment is name of the deployment object
kubectl set image deployments/server-deployment server=ocular2020/multi-server:$SHA
kubectl set image deployments/client-deployment client=ocular2020/multi-client:$SHA
kubectl set image deployments/worker-deployment worker=ocular2020/multi-worker:$SHA
kubectl set image deployments/postgres-deployment postgres=ocular2020/postgres-ddl:$SHA
kubectl set image deployments/jserv-deployment jserv=ocular2020/openliberty-openjdk11:$SHA
kubectl set image deployments/jserv-deployment jserv=ocular2020/jserv:$SHA





