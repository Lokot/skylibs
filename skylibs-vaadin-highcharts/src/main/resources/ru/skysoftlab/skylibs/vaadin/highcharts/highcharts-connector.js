window.ru_skysoftlab_skylibs_vaadin_highcharts_AbstractHighChart = function () {

	this.onStateChange = function () {
		// read state
		var domId = this.getState().domId;
		var hcjs = this.getState().hcjs;

		var connector = this;

		// evaluate highcharts JS which needs to define var "options"
		eval(hcjs);

		// set chart context
		var chart = Highcharts.chart(domId, options);
		document.getElementById(domId).chart = chart; 
	};

};
