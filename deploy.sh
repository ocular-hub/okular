docker build -t ocular2020/multi-client:latest -t ocular2020/multi-client:$SHA -f ./client/Dockerfile ./client
docker build -t ocular2020/multi-server:latest -t ocular2020/multi-server:$SHA -f ./server/Dockerfile ./server
docker built -t ocular2020/multi-worker:latest -t ocular2020/multi-worker:$SHA -f ./worker/Dockerfile ./worker
docker push ocular2020/multi-client:latest
docker push ocular2020/multi-server:latest
docker push ocular2020/multi-worker:latest
docker push ocular2020/multi-client:$SHA
docker push ocular2020/multi-server:$SHA
docker push ocular2020/multi-worker:$SHA
kubectl apply -f k8s
# server is the container name in the deployment config, check the containers section
# server-deployment is name of the deployment object
kubectl set image deployments/server-deployment server=ocular2020/multi-server:$SHA
kubectl set image deployments/client-deployment client=ocular2020/multi-client:$SHA
kubectl set image deployments/worker-deployment worker=ocular2020/multi-worker:$SHA

