(function ($) {
  if (ace.click_event == undefined) {
    ace.click_event = "click";
  }

  // Tooltip
  $.fn.initTooltip = function () {
    $('[data-rel=tooltip]').tooltip();
  }

  // Bootbox
  $.fn.initBootbox = function () {
    bootbox.setDefaults({ locale: "zh_CN" });
  }

  // Datatable delete action
  $.fn.initDTDeleteAction = function () {
    $('[data-method="delete"]').on("click", function (e) {
      e.preventDefault();
      var deleteURL = $(this).context.href;
      var backToIndex = deleteURL.substring(0, deleteURL.indexOf("delete"))

      bootbox.confirm("确定删除？", function (result) {
        if (result) {
          var form = $('<form method="post" action="' + deleteURL + '" method="post"></form>');
          form.html('<input type="hidden" value="DELETE" name="X-HTTP-Method-Override">');
          form.appendTo($('body'));
          form.submit();
        }
      });
    });
  }

  // Date time picker
  $.fn.initDatetimePicker = function () {
    $('[data-rel = "date-time-picker"]').datetimepicker().next().on(ace.click_event, function () {
      $(this).prev().focus();
    });
  }

  // Date picker
  $.fn.initDatePicker = function () {
    $('[data-rel="date-picker"]').datepicker({
      autoclose: true,
      todayHighlight: true
    })
    .next().on(ace.click_event, function () {
      $(this).prev().focus();
    });
  }

  // File upload
  $.fn.initFileUpload = function () {
    $('[type="file"]').ace_file_input({
      no_file: '没有文件 ...',
      btn_choose: '选择',
      btn_change: '修改',
      droppable: false,
      onchange: null,
      thumbnail: false //| true | large
      //whitelist:'gif|png|jpg|jpeg'
      //blacklist:'exe|php'
      //onchange:''
      //
    });

    $('[type="file"]').parent().attr("class", "ace-file-input col-xs-12 col-sm-6");
  }

  // Spinners
  $.fn.initSpinners = function (options) {
    var default_options = $.extend({}, options);
    var updown_options = $.extend({}, { value: 0, min: 0, touch_spinner: true, icon_up: 'ace-icon fa fa-caret-up', icon_down: 'ace-icon fa fa-caret-down' }, options);
    var minus_plus_options = $.extend({}, { value: 0, min: 0, on_sides: true, icon_up: 'ace-icon fa fa-plus smaller-75', icon_down: 'ace-icon fa fa-minus smaller-75', btn_up_class: 'btn-success', btn_down_class: 'btn-danger' }, options);

    $('[data-rel=spinner-default]')
      .ace_spinner(default_options)
      .on('change', function () {
      });

    $('[data-rel=spinner-up-down]')      .ace_spinner(updown_options)      .on('change', function () {
      });    $('[data-rel=spinner-minus-plus]')
      .ace_spinner(minus_plus_options)
      .on('change', function () {
      });
  }

  // Modal
  $.fn.initModal = function () {
    $('.modal-dialog').css('width', '950px');
  }

  // Initial all hotfix when loading.
  $.fn.initTooltip();
  $.fn.initBootbox();
  $.fn.initDTDeleteAction();
  $.fn.initDatetimePicker();
  $.fn.initDatePicker();
  $.fn.initFileUpload();
  $.fn.initSpinners();
  $.fn.initModal();
})(jQuery);