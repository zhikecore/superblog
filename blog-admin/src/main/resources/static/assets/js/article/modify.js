    var contentEditor;

    $(function () {
        contentEditor = editormd("content-editormd", {
            width: "90%",
            height: 640,
            syncScrolling: "single",
            path: "/assets/editorMd/lib/",
            saveHTMLToTextarea: true,
            emoji: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/Admin/Editor/UpImage",
        });
    });

    $("#content-editormd").on('paste', function (ev) {
        console.log(1);
        var items = (event.clipboardData || event.originalEvent.clipboardData).items;
        for (var index in items) {
            var item = items[index];
            if (item.kind === 'file') {
                var blob = item.getAsFile();
                var reader = new FileReader();
                reader.onload = function (event) {
                    //将剪切板中复制信息转换成一种base64格式字符串
                    var base64 = event.target.result;
                    //ajax上传图片
                    $.ajax({
                        url: "/Admin/Editor/UpladFilePIC",
                        type: 'post',
                        data: { 'base': base64 },
                        async: false,
                        dataType: 'json',
                        success: function (res) {
                            if (res.code == 1)//新一行的图片显示
                            {
                                //var imgStr = "\n![" + "image.png" + "](" + res.msg + ")";
                                var imgStr = "<section>";
                                imgStr += "<a href="+res.msg+" data-lightbox='example-set'>";
                                imgStr += "<img style='display: block; margin-left: auto; margin-right: auto;' src="+res.msg+" alt='' />";
                                imgStr += "</a> ";
                                imgStr += "</section> ";

                                console.log(imgStr);
                                contentEditor.insertValue(imgStr);
                            }
                        },
                        error: function () {
                            alert('图片上传错误');
                        }
                    });
                }
            }; // data url!
                var url = reader.readAsDataURL(blob);
            }
    });
