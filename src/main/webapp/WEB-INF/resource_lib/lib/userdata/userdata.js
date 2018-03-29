var indexdata = 
[	
    { text: '我的宝贝',isexpand:false, children: [ 
		{url:"util/loveB_myshop/resizable.htm",text:"全部宝贝" },
		{url:"util/loveB_myshop/drag.htm",text:"已买到的"},
		{url:"util/loveB_myshop/drag2.htm",text:"代付款"},
		{url:"util/loveB_myshop/dragresizable.htm",text:"代发货"},
		{url:"util/loveB_myshop/tip.htm",text:"代收货"}
	]
    },
    { text: '个人信息', isexpand: false, children: [
		{ url: "demos/filter/filter.htm", text: "基本信息" },
		{ url: "demos/filter/filterwin.htm", text: "修改密码" },
		{ url: "demos/filter/grid.htm", text: "修改头像" },
		{ url: "demos/filter/filterwin.htm", text: "绑定邮箱" },
		{ url: "demos/filter/filterwin.htm", text: "绑定手机" }
	]
    }, 
	{ text: '我的账户',isexpand:false, children: [ 
		{ url: "demos/dialog/dialogAll.htm", text: "账户余额" },
        { url: "demos/dialog/dialogParent.htm", text: "账户充值" },
		{url:"demos/dialog/dialogTarget.htm",text:"消费记录"},
		{url:"demos/dialog/dialogUrl.htm",text:"充值记录"}
	]},
]
var indexdata2 =
[
    { isexpand: "true", text: "表格", children: [
        { isexpand: "true", text: "可排序", children: [
		    { url: "dotnetdemos/grid/sortable/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/sortable/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "可分页", children: [
		    { url: "dotnetdemos/grid/pager/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/pager/server.aspx", text: "服务器" },
            { url: "dotnetdemos/grid/pager/server_scroll.aspx", text: "滚动分页" }
	    ]
        },
        { isexpand: "true", text: "树表格", children: [
		    { url: "dotnetdemos/grid/treegrid/tree.aspx", text: "树表格" }, 
		    { url: "dotnetdemos/grid/treegrid/tree2.aspx", text: "树表格2" }
	    ]
        }
    ]
    }
];
