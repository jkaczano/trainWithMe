$.ajax({
    url: "progressChart",
    success: function(result){
            var date = JSON.parse(result).date;
            var mass = JSON.parse(result).mass;
            var pullUps = JSON.parse(result).pullUps;
            var bmi = JSON.parse(result).bmi;
            drawLineChart(date,mass,pullUps,bmi);
    }
})

function drawLineChart(date,mass,pullUps,bmi){
Highcharts.chart('my_data',{
    chart: {
        type: 'line',
        width: 500
    },
    title: {
        text: 'My progress',
    },
    xAxis: {
        categories: date
    },
    series: [{
        name: 'Mass',
        data: mass
    },
    {
        name: 'Pull ups',
        data: pullUps
    },
    {
        name: 'BMI',
        data: bmi
    }]

});
}