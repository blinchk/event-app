import {ParticipantFormProps} from "../AddParticipantForm.tsx";
import {Col, Form, Row} from "react-bootstrap";

const PrivateEntityParticipantForm = (props: ParticipantFormProps) => {
    const { register } = props;

    return (
        <>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Eesnimi:</Form.Label>
                <Col>
                    <Form.Control {...register('firstName', { required: true })} />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Perekonnanimi:</Form.Label>
                <Col>
                    <Form.Control {...register('lastName', { required: true })} />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Isikukood:</Form.Label>
                <Col>
                    <Form.Control {...register('personalCode', { required: true })} />
                </Col>
            </Form.Group>
        </>
    )
}

export default PrivateEntityParticipantForm;