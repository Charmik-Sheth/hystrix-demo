import requests
import threading
import time
import statistics


def healthy_api_request():
    global healthy_api_errors
    global healthy_api_times

    start = time.time()
    resp = requests.get(url + "/healthy")
    end = time.time()
    healthy_api_lock.acquire()
    healthy_api_times.append((end - start) * 1000)
    if resp.status_code != 200:
        healthy_api_errors += 1
    healthy_api_lock.release()


def healthy_behaviour_burst():
    request_rate = 10
    threads = []
    run_time = 10

    for j in range(run_time):
        for i in range(request_rate):
            th = threading.Thread(target=healthy_api_request)
            threads.append(th)
            th.start()
            time.sleep(0.1)

    for th in threads:
        th.join()


def delayed_api_request(delay):
    global delayed_api_errors
    global delayed_api_times
    start = time.time()
    resp = requests.get("{}/delay/{}".format(url, delay))
    end = time.time()
    delayed_api_lock.acquire()
    delayed_api_times.append((end - start) * 1000)
    if resp.status_code != 200:
        delayed_api_errors += 1
    delayed_api_lock.release()


def delayed_behaviour_burst(delay):
    request_rate = 10
    threads = []
    run_time = 10

    for j in range(run_time):
        for i in range(request_rate):
            th = threading.Thread(target=delayed_api_request, args=(delay,))
            threads.append(th)
            th.start()
            time.sleep(0.1)

    for th in threads:
        th.join()


url = "http://localhost:8081"
healthy_api_times = []
healthy_api_errors = 0
healthy_api_lock = threading.Lock()

delayed_api_times = []
delayed_api_errors = 0
delayed_api_lock = threading.Lock()
delay = 5000

hthread = threading.Thread(target=healthy_behaviour_burst)
dthread = threading.Thread(target=delayed_behaviour_burst, args=(delay,))

dthread.start()
hthread.start()

dthread.join()
hthread.join()

sorted(healthy_api_times)
sorted(delayed_api_times)

hmean = statistics.mean(healthy_api_times)
hmedian = statistics.median(healthy_api_times)
hmax = max(healthy_api_times)
hmin = min(healthy_api_times)
h_error_percent = (healthy_api_errors + 0.0) / len(healthy_api_times)

dmean = statistics.mean(delayed_api_times)
dmedian = statistics.median(delayed_api_times)
dmax = max(delayed_api_times)
dmin = min(delayed_api_times)
d_error_percent = (delayed_api_errors + 0.0) / len(delayed_api_times)

print("Healthy API Stats")
print("Average : {}".format(hmean))
print("Median: {}".format(hmedian))
print("Min: {}".format(hmin))
print("Max: {}".format(hmax))
print("Error percent: {}".format(h_error_percent * 100))
print("----------------------------------------------")
print("Delayed API Stats")
print("Average : {}".format(dmean))
print("Median: {}".format(dmedian))
print("Min: {}".format(dmin))
print("Max: {}".format(dmax))
print("Error percent: {}".format(d_error_percent * 100))
