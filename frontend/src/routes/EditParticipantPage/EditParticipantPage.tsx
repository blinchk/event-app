import {ParticipantResponse, useParticipants} from "../../hooks/useParticipants.ts";
import {Col, Container, Row} from "react-bootstrap";
import {ContainerHeader} from "../../components/container/ContainerHeader.tsx";
import {useLoaderData} from "react-router-dom";
import {PaymentTypeResponse, usePaymentTypes} from "../../hooks/usePaymentTypes.ts";

import './EditParticipantPage.scss';
import ParticipantForm from "../../components/event/ParticipantForm/ParticipantForm.tsx";

export async function loader({ params }: any) {
    const { getParticipant } = useParticipants();
    const participant = await getParticipant(params.id);
    const { getPaymentTypes } = usePaymentTypes();
    const paymentTypes = await getPaymentTypes();
    return { participant, paymentTypes };
}

const EditParticipantPage = () => {
    // @ts-ignore
    const {
        participant,
        paymentTypes
    }: {
        participant: ParticipantResponse,
        paymentTypes: PaymentTypeResponse[]
    } = useLoaderData();

    return (
        <Container className="edit-participant-page-container">
            <ContainerHeader title="Osavõtja info"/>
            <Row className="justify-content-center">
                <Col xs={6}>
                    <h3 className="text-primary">Osavõtja info</h3>
                    <ParticipantForm paymentTypes={paymentTypes} actionType='edit' uuid={participant.uuid} />
                </Col>
            </Row>
        </Container>
    )
}

export default EditParticipantPage;