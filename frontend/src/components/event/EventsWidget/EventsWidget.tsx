import './EventsWidget.scss';
import {EventListItem, useEvents} from "../../../hooks/useEvents.ts";
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
    const [events, setEvents] = useState<EventListItem[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    const { getEvents } = useEvents();

    const isFutureEventsWidget = type === 'future';
    const searchParams = isFutureEventsWidget ? getFutureEventsSearchParams() : getPastEventsSearchParams();

    useEffect(() => {
        setIsLoading(true);
        getEvents(searchParams)
            .then((data) => {
                setEvents(data);
                setIsLoading(false);
            });
    }, []);

    return (
    <div className="events-widget">
        <div className="events-widget-title">
            {title}
        </div>
        <div className="events-widget-content">
            <div className="events-widget-events">
                {!isLoading && events?.map((event, index) => {
                    const formattedDate = toEstonianDateFormat(event.date);
                    return (
                        <div key={event.uuid}>
                            <Row className="events-widget-event">
                                <Col xs={7} className="text-start">{index + 1}. {event.name}</Col>
                                <Col xs={2} className="text-center">{formattedDate}</Col>
                                <Col className="text-center" xs={isFutureEventsWidget ? 2 : 3}>
                                    <LinkContainer to={`/event/${event.uuid}`}>
                                        <Button variant="light" className="no-outline-btn" size="sm">
                                            Osavõtjad
                                        </Button>
                                    </LinkContainer>
                                </Col>
                                {isFutureEventsWidget && (
                                    <Col xs={1} className="text-center">
                                        <Button className="no-outline-btn" variant="light" size="sm">
                                            <img src={removeIcon} alt="" style={{ height: 15 }}/>
                                        </Button>
                                    </Col>
                                )}
                            </Row>
                        </div>
                    )
                })}
                {!isLoading && events.length === 0 && (
                    <div>Üritused puuduvad</div>
                )}
            </div>
            {isFutureEventsWidget && (
                <LinkContainer to='/event/add'>
                    <Button variant='light' className="no-outline-btn">Lisa üritus</Button>
                </LinkContainer>
            )}
        </div>
    </div>);
}

export default EventsWidget;