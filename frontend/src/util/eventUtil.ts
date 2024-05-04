import {EventSearchParams} from "../hooks/useEvents.ts";

import moment from "moment";

const today = moment().format('yyyy-MM-DD');

export const getFutureEventsSearchParams = (): EventSearchParams => ({
    startDate: today
});

export const getPastEventsSearchParams = (): EventSearchParams => ({
    endDate: today
});

export const toEstonianDateFormat = (date: string) => moment(date).format('DD.MM.yyyy');