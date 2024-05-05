import {EventSearchParams} from "../hooks/useEvents.ts";

import moment from "moment";

export const getTodayDate = () => moment().format('YYYY-MM-DD');
export const getCurrentDateTime = () => moment().format('YYYY-MM-DD[T]HH:mm')

export const getFutureEventsSearchParams = (): EventSearchParams => ({
    startDate: getTodayDate()
});

export const getPastEventsSearchParams = (): EventSearchParams => ({
    endDate: getTodayDate()
});

export const toEstonianDateFormat = (date: string) => moment(date).format('DD.MM.yyyy');
export const toEstonianDateTimeFormat = (date: string) => moment(date).format('DD.MM.yyyy HH:mm');