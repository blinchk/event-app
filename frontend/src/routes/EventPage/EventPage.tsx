import {Col, Container, Row} from "react-bootstrap";

import './EventPage.scss';
import {ContainerHeader} from "../../components/container/ContainerHeader.tsx";
import {useLoaderData} from "react-router-dom";
import {EventResponse, useEvents} from "../../hooks/useEvents.ts";
import ParticipantForm from "../../components/event/ParticipantForm/ParticipantForm.tsx";
import EventData from "../../components/event/EventData/EventData.tsx";
import {PaymentTypeResponse, usePaymentTypes} from "../../hooks/usePaymentTypes.ts";

export async function loader({ params }: any) {
    const { getEvent } = useEvents();
    const event = await getEvent(params.id);
    const { getPaymentTypes } = usePaymentTypes();
    const paymentTypes = await getPaymentTypes();
    return { event, paymentTypes };
}

const EventPage = () => {
    // @ts-ignore
    const { event, paymentTypes }: { event: EventResponse, paymentTypes: PaymentTypeResponse[] } = useLoaderData();

    return (
        <Container className="event-page-container">
            <ContainerHeader title="Osavõtjad"/>
            <Row className="justify-content-center">
                <Col xs={6}>
                    <h3 className="text-primary">Osavõtjad</h3>
                    <div className="event-data-wrapper">
                        <EventData event={event} />
                    </div>
                    <h3 className="text-primary">Osavõtjate lisamine</h3>
                    <div className="participant-create-form-wrapper">
                        <ParticipantForm paymentTypes={paymentTypes} displayEntityTypeOptions actionType='create' />
                    </div>
                </Col>
            </Row>
        </Container>
    );
}

export default EventPage;