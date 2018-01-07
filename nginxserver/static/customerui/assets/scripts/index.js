var Index = function () {

    return {
        initLayerSlider: function () {
            $('#layerslider').layerSlider({
                skin : 'fullwidth',
                thumbnailNavigation : 'hover',
                hoverPrevNext : false,
                responsive : false,
                responsiveUnder : 960,
                sublayerContainer : 960
            });
        }
    };

}();