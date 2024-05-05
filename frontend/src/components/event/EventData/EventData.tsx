import {EventParticipant, EventResponse} from "../../../hooks/useEvents.ts";
import {Button, Col, Row} from "react-bootstrap";
import {toEstonianDateTimeFormat} from "../../../util/eventUtil.ts";

import './EventData.scss';
import {useParticipants} from "../../../hooks/useParticipants.ts";
import {useNavigate} from "react-router-dom";

export interface EventDataProps {
    event: EventResponse;
}

const EventData = ({ event }: EventDataProps) => {
    const { deleteParticipant } = useParticipants();
    const navigate = useNavigate();

    const handleRemoveClick = (participantUuid: string) => {
        deleteParticipant(participantUuid).then(() => navigate('.', { replace: true }))
    }

    return (
        <dl className="event-data">
            <Row>
                <Col xs={3} as="dt">
                    Ürituse nimi:
                </Col>
                <Col xs={9} as="dd">
                    {event.name}
                </Col>
            </Row>
            <Row>
                <Col xs={3} as="dt">
                    Toimumisaeg:
                </Col>
                <Col xs={9} as="dd">
                    {toEstonianDateTimeFormat(event.time)}
                </Col>
            </Row>
            <Row>
                <Col xs={3} as="dt">
                    Koht:
                </Col>
                <Col xs={9} as="dd">
                    {!!event.location && (<dd>{event.location}</dd>)}
                    {!event.location && (<dd>Koht pole määratud</dd>)}
                </Col>
            </Row>
            <Row>
                <Col xs={3} as="dt">
                    Osavõtjad:
                </Col>
                <Col xs={9} as="dd">
                    {event.participants &&
                        event.participants.map((participant: EventParticipant, index: number) => (
                            <Row className="event-participant-row" key={participant.uuid}>
                                <Col xs={1}>{index + 1}.</Col>
                                <Col xs={3}>{participant.name}</Col>
                                <Col xs={3}>{participant.code}</Col>
                                <Col xs={2}><Button variant="light" className="no-outline-btn">Vaata</Button></Col>
                                <Col xs={3}>
                                    <Button
                                        variant="light"
                                        className="no-outline-btn"
                                        onClick={() => handleRemoveClick(participant.uuid)}>
                                        Kustuta
                                    </Button>
                                </Col>
                            </Row>
                        ))
                    }
                    {event.participants.length === 0 && "Ürituse osavõtjad puuduvad"}
                </Col>
            </Row>
        </dl>
    )
}

export default EventData;