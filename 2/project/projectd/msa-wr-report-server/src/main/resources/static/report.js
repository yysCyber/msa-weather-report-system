$("#selectCityId").on('change', function () {
    var cityId = $("#selectCityId").val();
    window.location.href = '/weather/report/'+ cityId;
});