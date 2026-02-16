import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend } from 'k6/metrics';

export let options = {
    stages: [
        { duration: '30s', target: 5 },
        { duration: '1m', target: 40 },
        { duration: '30s', target: 0 },
    ],
};

let diagnosticTrend = new Trend('diagnostic_duration');

export default function () {
    // LOGIN
    const loginPayload = 'email=elharrab.hi@gmail.com&password=ALIALIALI';

    const loginRes = http.post('http://server:8080/login', loginPayload, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        redirects: 0,
    });

    check(loginRes, {
        'login succeeded': (r) => r.status === 302 && r.headers['Location'] === 'home',
    });

    sleep(1);

    // DIAGNOSTIC
    const answersStr = '1,1,0,0,1,60-170,2,0-2,1,2,0,1,0,1,1';
    let start = new Date().getTime();

    const diagRes = http.post('http://server:8080/diagnostic', { answers: answersStr });
    diagnosticTrend.add(new Date().getTime() - start);

    check(diagRes, {
        'diagnostic submission succeeded': (r) => r.status === 200 && r.body.includes('Risk Level'),
    });

    sleep(1);
}
