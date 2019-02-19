/**
 * Class       : JCal
 * Description : Change georgian date to jalali(solar) date
 * Author      : vv0z <i.beshkar@gmail.com>
 */


let mDate;
let day;
let month;
let year;


export default class JCal {

    constructor(date: string) {
        mDate = date;
        toJalali();
    }

    /**
     * Get init date
     */
    static getDate(date: string) {
        return new this(date);
    }

    /**
     * Return today date
     */
    static getCurrentDate() {
        let dt = new Date();
        let dy = dt.getDate().toString();
        let mn = (dt.getMonth() + 1).toString();
        let yr = dt.getFullYear();
        mDate = yr + '-' + mn + '-' + dy;
        return toJalali();
    }

    /**
     * Return pretty shape of date
     */
    getPrettyJDate() {
        return this.getDayOfWeek() + ' ' + this.getJDay() + ' ' + this.getMonthOfYear() + ' ' + this.getJYear();
    }

    /**
     * Return jalali day
     */
    getJDay() {
        return day.toString();
    }

    /**
     * Return jalali month
     */
    getJMonth() {
        return Math.floor(month).toString();
    }

    /**
     * Return jalali year
     */
    getJYear() {
        return year.toString();
    }

    /**
     * Return day of jalali(solar) week
     */
    getDayOfWeek() {
        let dt = mDate.split('-');
        if (dt[1].length < 2)
            dt[1] = '0' + dt[1];
        if (dt[2].length < 2)
            dt[2] = '0' + dt[2];
        let a = new Date(dt[0] + '-' + dt[1] + '-' + dt[2]);
        switch (a.getDay()) {
            case 0:
                return 'یکشنبه';
            case 1:
                return 'دوشنبه';
            case 2:
                return 'سه شنبه';
            case 3:
                return 'چهارشنبه';
            case 4:
                return 'پنج شنبه';
            case 5:
                return 'جمعه';
            case 6:
                return 'شنبه';
        }
    }

    /**
     * Return month of jalali(solar) year
     */
    getMonthOfYear() {
        switch (this.getJMonth()) {
            case '1':
                return 'فروردین';
            case '2':
                return 'اردیبهشت';
            case '3':
                return 'خرداد';
            case '4':
                return 'تیر';
            case '5':
                return 'مرداد';
            case '6':
                return 'شهریور';
            case '7':
                return 'مهر';
            case '8':
                return 'آبان';
            case '9':
                return 'آذر';
            case '10':
                return 'دی';
            case '11':
                return 'بهمن';
            case '12':
                return 'اسفند';
        }
    }
}

/**
 * Change date to jalali date
 */
function toJalali() {

    const DATE_REGEX = /^((19)\d{2}|(2)\d{3})-(([1-9])|((1)[0-2]))-([1-9]|[1-2][0-9]|(3)[0-1])$/;

    if (DATE_REGEX.test(mDate)) {

        let dt = mDate.split('-');
        let ld;

        let mYear = parseInt(dt[0]);
        let mMonth = parseInt(dt[1]);
        let mDay = parseInt(dt[2]);

        let buf1 = [12];
        let buf2 = [12];

        buf1[0] = 0;
        buf1[1] = 31;
        buf1[2] = 59;
        buf1[3] = 90;
        buf1[4] = 120;
        buf1[5] = 151;
        buf1[6] = 181;
        buf1[7] = 212;
        buf1[8] = 243;
        buf1[9] = 273;
        buf1[10] = 304;
        buf1[11] = 334;

        buf2[0] = 0;
        buf2[1] = 31;
        buf2[2] = 60;
        buf2[3] = 91;
        buf2[4] = 121;
        buf2[5] = 152;
        buf2[6] = 182;
        buf2[7] = 213;
        buf2[8] = 244;
        buf2[9] = 274;
        buf2[10] = 305;
        buf2[11] = 335;

        if ((mYear % 4) !== 0) {
            day = buf1[mMonth - 1] + mDay;

            if (day > 79) {
                day = day - 79;
                if (day <= 186) {
                    switch (day % 31) {
                        case 0:
                            month = day / 31;
                            day = 31;
                            break;
                        default:
                            month = (day / 31) + 1;
                            day = (day % 31);
                            break;
                    }
                    year = mYear - 621;
                } else {
                    day = day - 186;

                    switch (day % 30) {
                        case 0:
                            month = (day / 30) + 6;
                            day = 30;
                            break;
                        default:
                            month = (day / 30) + 7;
                            day = (day % 30);
                            break;
                    }
                    year = mYear - 621;
                }
            } else {
                if ((mYear > 1996) && (mYear % 4) === 1) {
                    ld = 11;
                } else {
                    ld = 10;
                }
                day = day + ld;

                switch (day % 30) {
                    case 0:
                        month = (day / 30) + 9;
                        day = 30;
                        break;
                    default:
                        month = (day / 30) + 10;
                        day = (day % 30);
                        break;
                }
                year = mYear - 622;
            }
        } else {
            day = buf2[mMonth - 1] + mDay;

            if (mYear >= 1996) {
                ld = 79;
            } else {
                ld = 80;
            }
            if (day > ld) {
                day = day - ld;

                if (day <= 186) {
                    switch (day % 31) {
                        case 0:
                            month = (day / 31);
                            day = 31;
                            break;
                        default:
                            month = (day / 31) + 1;
                            day = (day % 31);
                            break;
                    }
                    year = mYear - 621;
                } else {
                    day = day - 186;

                    switch (day % 30) {
                        case 0:
                            month = (day / 30) + 6;
                            day = 30;
                            break;
                        default:
                            month = (day / 30) + 7;
                            day = (day % 30);
                            break;
                    }
                    year = mYear - 621;
                }
            } else {
                day = day + 10;

                switch (day % 30) {
                    case 0:
                        month = (day / 30) + 9;
                        day = 30;
                        break;
                    default:
                        month = (day / 30) + 10;
                        day = (day % 30);
                        break;
                }
                year = mYear - 622;
            }

        }

        return year.toString() + "-" + Math.floor(month).toString() + "-" + day.toString();
    }
}
