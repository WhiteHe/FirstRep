$(function(){
	 var node = $('.side-nav > .nav li:has(.nav)').find('.active');
	 if(node){
		$(node).parent().show();
	 }
    $('.side-nav > .nav li:has(.nav)').find('> a').on('click',function(){
        $(this).next().toggle();
        $(this).find('span.pull-right').toggleClass("glyphicon-menu-down").toggleClass("glyphicon-menu-right");
    })
})

function chooseAll(val){
	$('tbody :input').each(function(){
		this.checked = val;
	});
}
