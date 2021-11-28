$(function () {
    $(".checkall").change(function () {
        $(".j-checkbox, .checkall").prop("checked", $(this).prop("checked"));
        getSum();
        if ($(this).prop("checked")) {
            $(".cart-item").addClass("check-cart-item");
        } else {
            $(".cart-item").removeClass("check-cart-item");
        }


        $(".cart-filter-bar").children("em").eq(1).text($(".j-checkbox:checked").length);

    });

    $(".j-checkbox").change(function () {
        if ($(".j-checkbox:checked").length === $(".j-checkbox").length) {
            $(".checkall").prop("checked", true);
        } else {
            $(".checkall").prop("checked", false);
        }
        getSum();
        if ($(this).prop("checked")) {
            $(this).parents(".cart-item").addClass("check-cart-item");
        } else {
            $(this).parents(".cart-item").removeClass("check-cart-item");
        }

        $(".cart-filter-bar").children("em").eq(1).text($(".j-checkbox:checked").length);

    });

    $(".increment").click(function () {

        var n = $(this).siblings(".itxt").val();
        n++;


        if (n > 200) {
            $(".dialog").show();
            $(".yes").bind("click", function (e) {
                $(".dialog").hide();
            });
            $(".dialog-close").bind("click", function (e) {
                $(".dialog").hide();
            });
            n = 200;
        }

        $(this).siblings(".itxt").val(n);

        var p = $(this).parents(".p-num").siblings(".p-price").html();
        p = p.substr(1);
        var price = (p * n).toFixed(2);
        $(this).parents(".p-num").siblings(".p-sum").html("¥" + price);
        getSum();
    });


    $('.decrement').bind("mouseenter", function (e) {
        var n = $(this).siblings(".itxt").val();
        if (n == 1) {
            $(this).text('!');
            return false;
        }
    });
    $('.decrement').bind("mouseleave", function (e) {
        $(this).text('-');
    });


    $(".decrement").click(function () {

        var n = $(this).siblings(".itxt").val();
        if (n == 1) {
            return false;
        }
        n--;
        $(this).siblings(".itxt").val(n);

        var p = $(this).parents(".p-num").siblings(".p-price").html();
        p = p.substr(1);
        var price = (p * n).toFixed(2);
        $(this).parents(".p-num").siblings(".p-sum").html("¥" + price);
        getSum();
    });

    $(".itxt").change(function () {

        var n = $(this).val();

        if (n > 200) {
            $(".dialog").show();
            $(".yes").bind("click", function (e) {
                $(".dialog").hide();
            });
            $(".dialog-close").bind("click", function (e) {
                $(".dialog").hide();
            });
            $(".itxt").val("200");
        }


        var p = $(this).parents(".p-num").siblings(".p-price").html();
        p = p.substr(1);
        var price = (p * n).toFixed(2);
        $(this).parents(".p-num").siblings(".p-sum").html("￥" + price);
        getSum();
    });


    $(".itxt").click(function (e) {
        e.preventDefault();
        this.select();
    });


    $(".itxt").bind("keyup", function () {
        $(this).val($(this).val().replace(/[\D]/g, ""));
    });

    function getSum() {

        var count = 0;
        var item = $(".j-checkbox:checked").parents(".cart-item");
        item.find(".itxt").each(function (i, ele) {
            count += parseInt($(ele).val());
        });
        $(".amount-sum em").text(count);

        var money = 0;
        item.find(".p-sum").each(function (i, ele) {
            money += parseFloat($(ele).text().substr(1));
        });
        $(".price-sum em").text("¥" + money.toFixed(2));
    }

    getSum();

    $(".p-action a").click(function () {
        $(this).parents(".cart-item").remove();

        $(".cart-filter-bar").children("em").eq(1).text($(".j-checkbox:checked").length);
        getSum();
    });

    $(".remove-batch").click(function () {
        $(".j-checkbox:checked").parents(".cart-item").remove();

        $(".cart-filter-bar").children("em").eq(1).text($(".j-checkbox:checked").length);
        getSum();
    });

    $(".clear-all").click(function () {
        $(".cart-item").remove();

        $(".cart-filter-bar").children("em").eq(1).text($(".j-checkbox:checked").length);
        getSum();
    })

});

