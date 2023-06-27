/**
 * 判断是字符串是否为null or 空串
 * @param str 链接地址
 */
export function isNullOrEmptyString(str: string | any): boolean {
    // eslint-disable-next-line eqeqeq
    return str == undefined || str == null || str.length === 0; // Or any other logic, removing whitespace, etc.
}

/**
 * 去除html标签
 * @param str
 * @param len 是否截取程度
 */
export function cleanHtml(str: string, len?: number): string {
    if (str == null || str === '') {
        return '';
    }

    let dt = str.replace(/<[^>]+>/g, ''); // 去掉所有的html标记
    if (len != null && dt.length > len) {
        dt = dt.substring(0, 300);
    }

    return dt;
}

/**
 * Hash code
 * @param str
 */
export function hashcode(str: string) {
    let hash = 0;
    let i;
    let chr;
    let len;
    if (str.length === 0) {
        return hash;
    }
    for (i = 0, len = str.length; i < len; i++) {
        chr = str.charCodeAt(i);
        hash = (hash << 5) - hash + chr;
        hash |= 0; // Convert to 32bit integer
    }
    return hash;
}

/**
 *  函数:格式化字符串
 @description 调用方式:formatString("api/values/{id}/{name}",{id:101,name:"test"});  formatString("api/values/{0}/{1}",101,"test");
 * @param str 字符串模板
 * @param data 数据
 */
export function formatString(str: string, data: any): string {
    if (!str || data === undefined) {
        return str;
    }

    if (typeof data === 'object') {
        for (let key in data) {
            if (data.hasOwnProperty(key)) {
                str = str.replace(new RegExp('{' + key + '}', 'g'), data[key]);
            }
        }
    } else {
        let args = arguments;
        let reg = new RegExp('{([0-' + (args.length - 1) + '])}', 'g');
        return str.replace(reg, function (match, index) {
            return args[index - -1];
        });
    }
    return str;
}

/**
 * 格式化占位符输出
 * @param num
 * @param pad
 */
export function padFormat(num: number, length: number, pad: string = '00000000000000000000000000000') {
    const y = pad + num; //爱几个0就几个，自己够用就行
    return y.substr(y.length - length);
}
