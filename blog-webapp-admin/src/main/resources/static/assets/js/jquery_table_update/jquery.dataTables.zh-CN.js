var dataTable = {
  language: {
    oAria: {
      sSortAscending: ' - 点击升序排列',
      sSortDescending: ' - 点击降序排列'
    },
    oPaginate: {
      sFirst: "首页",
      sPrevious: "上页",
      sNext: "下页",
      sLast: "末页"
    },
    sEmptyTable: '没有任何数据',
    sInfo: '显示 _START_ 到 _END_ 条数据，共有 _TOTAL_ 条数据',
    sInfoEmpty: '没有任何数据',
    sInfoFiltered: ' - 从 _MAX_ 条数据过滤',
    sInfoPostFix: '',
    sInfoThousands: "'",
    sLengthMenu: '_MENU_',
    sLoadingRecords: '请等待，数据加载中...',
    sProcessing: '数据正在加载中...',
    sSearch: '搜索',
    sZeroRecords: '没有匹配结果'
  },
  lengthMenu: [[10, 25, 50, 100], ['每页10条数据', '每页25条数据', '每页50条数据', '每页100条数据']]
};

$.extend(true, $.fn.dataTable.defaults, {
  sPaginationType: 'bootstrap',
  oLanguage: dataTable.language,
  bProcessing: false,
  bServerSide: true,
  bAutoWidth: true,
  bDeferRender: true,
  iDisplayLength: 25,


  sDom: '"top"i', //隐藏searchbox,

  fnServerData: function (sUrl, aoData, fnCallback, oSettings) {
    return oSettings.jqXHR = $.ajax({
      url: $(oSettings.nTable).data('source'),
      data: aoData,
      type: 'GET',
      dataType: 'json',
      success: fnCallback,
      cache: true
    });
  },

  fnDrawCallback: function (row, data, dataIndex, oSetting) {
    $(this).initTooltip();
    $(this).initBootbox();
    $(this).initDTDeleteAction();
  }
});

$.fn.dataTable.defaults.aLengthMenu = dataTable.lengthMenu;