import {ParticipantFormProps} from "../AddParticipantForm.tsx";
import {Col, Form, Row} from 'react-bootstrap';

const LegalEntityParticipantForm = (props: ParticipantFormProps) => {
    const { register } = props;

    return (
        <>
            <Form.Group as={Row} className='mb-3'>
                <Form.Label column>Nimi:</Form.Label>
                <Col>
                    <Form.Control {...register('name', { required: true })} />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Registrikood:</Form.Label>
                <Col>
                    <Form.Control {...register('registryCode', { required: true })} />
                </Col>
            </Form.Group>
        </>
    )
}

export default LegalEntityParticipantForm;