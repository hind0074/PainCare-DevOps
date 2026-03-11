const doughnutChartConfig = (dataObject, title = "") => ({
    type: 'doughnut',
    data: {
        labels: Object.keys(dataObject),
        datasets: [
            {
                label: '  ',
                data: Object.values(dataObject)
            }
        ]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: false,
                text: title
            },
            datalabels: {
	            formatter: (value, ctx) => {
	                const sum = Object.values(dataObject).reduce((a,b) => a+b);
	                const percentage = (value*100 / sum).toFixed(0)+"%";
	                return percentage;
	            },
	            color: '#fff',
	        },
        }
    },
});

// locations
new Chart(document.getElementById("locations-chart"), doughnutChartConfig(locationsObject, "Pain Locations"));

// symptoms
new Chart(document.getElementById("symptoms-chart"), doughnutChartConfig(symptomsObject, "Symptoms"));

// pain worse
new Chart(document.getElementById("worse-pain-chart"), doughnutChartConfig(worsePainObject, "What Makes Pain Worse"));

// symptoms
new Chart(document.getElementById("feelings-chart"), doughnutChartConfig(feelingsObject, "Feelings"));

const data = {
    labels: dates.map(d => new Date(d).toLocaleDateString()),
    datasets: [
        {
            label: 'Pain Evolustion Last 7 Days',
            data: datapoints,
            fill: false,
            cubicInterpolationMode: 'monotone',
            tension: 0.4,
            backgroundColor: "#fa859a"
        }
    ]
};

new Chart(document.getElementById('pain-level-chart'), {
    type: 'bar',
    data: data,
    options: {
        responsive: true,
        interaction: {
            intersect: false,
        },
        scales: {
            x: {
                display: true,
                title: {
                    display: true
                }
            },
            y: {
                display: true,
                title: {
                    display: true,
                    text: 'Pain'
                },
                suggestedMin: 0,
                suggestedMax: 10
            }
        },
        plugins: {
			datalabels: {
	            formatter: (value, ctx) => {
	                return "";
	            },
	            
	        },
		}
    },
})