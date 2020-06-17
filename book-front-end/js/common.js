DOMAIN = "http://localhost:8080";

PAGEINFO = {
    hadLogin: false,
    user: {},
}

function parseParamFormLocation(location) {
    let param = {};
    if (location.href.indexOf("?") !== -1) {
        let preTemp;
        location.href.split("?")[1].split("&").forEach((e) => {
            e.split("=").forEach((e1, index) => {
                if (index % 2 === 0) {
                    preTemp = e1;
                } else {
                    param[preTemp] = e1
                }
            })
        })
    }
    return param
}

function dateFormat(fmt, date) {
    let ret;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length === 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        }
        ;
    }
    ;
    return fmt;
}

function submit(_flag) {
    let submitData = {};
    $("#loginForm input").each((index, e) => {
        submitData[e.name] = e.value;
    })

    if (_flag === 0) {
        axios.post(DOMAIN + '/user/login', submitData, {
            withCredentials: true
        }).then(response => {
            if (response.data.code === 200) {
                alert("成功")
                window.location.reload();
            }else{
                alert("登陆失败")
                window.location.reload();
            }

        }).catch(function (error) {
            console.log(error);
        });
    } else if (_flag === 1) {
        axios.post(DOMAIN + '/user/add', submitData, {
            withCredentials: true
        }).then(response => {
            if (response.data.code === 200) {
                alert("成功")
                window.location.reload();
            }

        }).catch(function (error) {
            console.log(error);
        });
    } else if (_flag === 2) {
        let value = "";
        $("#changePw").each((index, e) => {
            value = e.value;
        });

        if (value === "") {
            alert("请输入密码")
        }
        axios.post(DOMAIN + '/user/update', {
            id: vue_header.user.id,
            name: vue_header.user.name,
            password: value
        }, {
            withCredentials: true
        }).then(response => {
            if (response.data.code === 200) {
                alert("密码修改成功")
                window.location.reload();
            }

        }).catch(function (error) {
            console.log(error);
        });
    }
    else if (_flag === 3) {
        submitData.role = 1;
        axios.post(DOMAIN + '/user/login', submitData, {
            withCredentials: true
        }).then(response => {
            if (response.data.code === 200) {
                alert("成功")
                window.location.reload();
            }else{
                alert("登陆失败")
                window.location.reload();
            }

        }).catch(function (error) {
            console.log(error);
        });
    }
}


let vue_header = new Vue({
    el: '#header',
    data: {
        hadLogin: false,
        user: {}
    },
    mounted() {
        axios.get(DOMAIN + '/user/hadLogin', {
            withCredentials: true

        }).then(response => {
            if (response.data.code === 200) {
                this.hadLogin = true;
                this.user = response.data.data;
                PAGEINFO.hadLogin = this.hadLogin;
                PAGEINFO.user = this.user;
            }
        }).catch(function (error) {
            console.log(error);
        });
    },
    methods: {
        loginOut() {
            axios.get(DOMAIN + '/user/loginOut', {
                withCredentials: true
            }).then(response => {
                if (response.data.code === 200) {
                    alert("成功")
                    window.location.reload();
                }

            }).catch(function (error) {
                console.log(error);
            });
        }
    }
});