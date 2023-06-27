import {AppConfig} from "../config/appConfig";
import {isNullOrEmptyString} from "../helpers/stringHelper";
import dayjs from "dayjs";

/**
 * 缓存服务
 */
class cacheService {

    /**
     * 获取utc time
     */
    getUTCTime(): number {
        return dayjs().unix();
        //getUnixTime(new Date());
    }

    /**
     * 添加到缓存
     * @param key
     * @param data
     */
    add(key: string, data: any): boolean {

        if (data === undefined || data === null)
            localStorage.removeItem(key)
        else
            localStorage.setItem(key, data);

        return true;
    }

    /**
     * 获取缓存中的数据
     * @param key
     * @param autoRefresh 自动刷新
     */
    get(key: string): any {
        let dt = localStorage.getItem(key);
        if (dt === undefined)
            dt = null;

        return dt;
    }

    /**
     *
     * @param key
     */
    remove(key: string)  {
        localStorage.removeItem(key);
    }

    /**
     *
     */
    clear( )  {
        localStorage.clear();
    }

}

export function getCacheKey(key: string): string {

    if (isNullOrEmptyString(key))
        return "";

    const withKey = AppConfig.id + "." + key;

    return withKey;
}

export default new cacheService()
