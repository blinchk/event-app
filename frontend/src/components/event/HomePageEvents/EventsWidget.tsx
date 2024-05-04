import './EventsWidget.scss';
import {EventResponse, useEvents} from "../../../hooks/useEvents.ts";
import {useEffect, useState} from "react";
import {Button, Col, Row} from "react-bootstrap";
import {getFutureEventsSearchParams, getPastEventsSearchParams, toEstonianDateFormat} from "../../../util/eventUtil.ts";
import {LinkContainer} from "react-router-bootstrap";
import removeIcon from '../../../assets/remove.svg';

export interface HomePageEventsProps {
    title: string;
    type: 'past' | 'future';
}

const EventsWidget = (props: HomePageEventsProps) => {
    const { title, type } = props;
    const [events, setEvents] = useState<EventResponse[]>();

    const { getEvents } = useEvents();

    const isFutureEventsWidget = type === 'future';
    const searchParams = isFutureEventsWidget ? getFutureEventsSearchParams() : getPastEventsSearchParams();

    useEffect(() => {
        getEvents(searchParams).then((data) => setEvents(data))
    }, []);

    return (
    <div className="events-widget">
        <div className="events-widget-title">
            {title}
        </div>
        <div className="event-widget-events">
            {events?.map((event, index) => {
                const formattedDate = toEstonianDateFormat(event.date);
                return (
                    <div key={event.uuid}>
                        <Row className="events-widget-event">
                            <Col xs={7}>{index + 1}. {event.name}</Col>
                            <Col xs={2}>{formattedDate}</Col>
                            <Col xs={isFutureEventsWidget ? 2 : 3}>
                                <Button variant="light" className="no-outline-btn" size="sm">
                                    <span className="no-outline-btn-text">Osavõtjad</span>
                                </Button>
                            </Col>
                            {isFutureEventsWidget && (
                                <Col xs={1}>
                                    <Button className="no-outline-btn" variant="light" size="sm">
                                        <img src={removeIcon} alt="" style={{ height: 15 }}/>
                                    </Button>
                                </Col>
                            )}
                        </Row>
                    </div>
                )
            })}
        </div>
        {isFutureEventsWidget && (
            <LinkContainer to='/event/add'>
                <div className="add-event-button">
                    <Button variant='light'>Lisa üritus</Button>
                </div>
            </LinkContainer>
        )}
    </div>);
}

export default EventsWidget;