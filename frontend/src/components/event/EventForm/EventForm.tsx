import {Button, Col, Form, Row} from "react-bootstrap";
import {getCurrentDateTime} from "../../../util/eventUtil.ts";
import {EventRequest, useEvents} from "../../../hooks/useEvents.ts";
import {useNavigate} from "react-router-dom";
import {SubmitHandler, useForm} from "react-hook-form";

const EventForm = () => {
    const { addEvent } = useEvents();
    const {handleSubmit, register} = useForm<EventRequest>();
    const navigate = useNavigate();

    const onSubmit: SubmitHandler<EventRequest> = (data) => {
        addEvent({
            ...data
        }).then(() => navigate(`/`))

    }

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Ürituse nimi</Form.Label>
                <Col>
                    <Form.Control {...register('name', {required: true})} />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Toimumisaeg</Form.Label>
                <Col>
                    <Form.Control
                        required
                        type="datetime-local"
                        min={getCurrentDateTime()}
                        placeholder='pp.kk.aaaa hh:mm'
                        {...register('time', {required: true})}
                    />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Toimumise koht</Form.Label>
                <Col>
                    <Form.Control {...register('location', { required: true })} />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3">
                <Form.Label column>Lisainfo</Form.Label>
                <Col>
                    <Form.Control as="textarea"
                                  {...register('description', { maxLength: 1000 })}
                    />
                </Col>
            </Form.Group>

            <Button variant="secondary">
                Tagasi
            </Button>

            <Button variant="primary" type="submit">
                Salvesta
            </Button>
        </Form>
    )
}

export default EventForm;