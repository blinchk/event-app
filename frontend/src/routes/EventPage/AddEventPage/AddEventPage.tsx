import {Col, Container, Row} from "react-bootstrap";
import EventForm from "../../../components/event/EventForm/EventForm.tsx";
import {ContainerHeader} from "../../../components/container/ContainerHeader.tsx";

import './AddEventPage.scss';


const AddEventPage = () => (
    <Container className="add-event-page-container">
        <ContainerHeader title="Ürituse lisamine" />
        <Row className="justify-content-center">
            <Col xs={6}>
                <h3 className="text-primary">Ürituse lisamine</h3>
                <EventForm/>
            </Col>
        </Row>
    </Container>
);

export default AddEventPage;