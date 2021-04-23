$.ajax({
    url: "progressChart",
    success: function(result){
            var date = JSON.parse(result).date;
            var mass = JSON.parse(result).mass;
            var pullUps = JSON.parse(result).pullUps;
            drawLineChart(date,mass,pullUps);
    }
})

function drawLineChart(date,mass,pullUps){
Highcharts.chart('my_data',{
    chart: {
        type: 'line',
        width: 500
    },
    title: {
        text: 'LineChart',
    },
    xAxis: {
        categories: date
    },
    series: [{
        data: mass
    },
    {
        data: pullUps
    }
    ]

});
}