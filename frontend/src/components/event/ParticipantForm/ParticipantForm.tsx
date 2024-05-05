import {Alert, Stack, Col, Form, Row, Button} from "react-bootstrap";
import {SubmitHandler, useForm, UseFormRegister} from "react-hook-form";
import {
    useParticipants, ParticipantFormData, ParticipantResponse
} from "../../../hooks/useParticipants.ts";
import {useNavigate, useParams} from "react-router-dom";
import LegalEntityParticipantForm from "./LegalEntityParticipantForm/LegalEntityParticipantForm.tsx";
import PrivateEntityParticipantForm from "./PrivateEntityParticipantForm/PrivateEntityParticipantForm.tsx";
import {PaymentTypeResponse} from "../../../hooks/usePaymentTypes.ts";
import {useState} from "react";

export interface ParticipantFormProps {
    displayEntityTypeOptions?: true;
    paymentTypes: PaymentTypeResponse[];
    actionType: 'create' | 'edit';
    uuid?: string;
}

export interface RegisterParticipantFormOptions {
    register: UseFormRegister<ParticipantFormData>;
}

export interface PaymentTypeSelectProps {
    paymentTypes: PaymentTypeResponse[];
}

const PaymentTypeSelect = ({ paymentTypes, register }: PaymentTypeSelectProps & RegisterParticipantFormOptions) => (
    <Form.Select {...register('paymentTypeId', { required: true })}>
        {paymentTypes.map((paymentType) => (
            <option key={paymentType.id} value={paymentType.id}>{paymentType.title}</option>
        ))}
    </Form.Select>
)

const ParticipantForm = ({ paymentTypes, displayEntityTypeOptions, actionType, uuid }: ParticipantFormProps) => {
    const { addParticipant, saveParticipant, getParticipant } = useParticipants();
    const [eventUuid, setEventUuid] = useState<string>();
    const [error, setError] = useState<string>();

    const {
        handleSubmit,
        register,
        watch,
        reset
    } = useForm<ParticipantFormData>({
        defaultValues: actionType === 'edit' && uuid ?
            async () => await getParticipant(uuid)
                .then((response) => {
                    setEventUuid(response.eventUuid);
                    return response;
                }) :
            {
                entityType: "private",
                paymentTypeId: 1
            }
    });

    const navigate = useNavigate();

    const { id } = useParams<string>();
    const entityType = watch('entityType', 'private');

    const updateData = () => navigate('.', { replace: true });

    const onSubmit: SubmitHandler<ParticipantFormData> = (data: ParticipantFormData) => {
        const createCallback = () => {
            updateData();
            reset();
        }
        const editCallback = (response: ParticipantResponse) => {
            navigate(`/event/${response.eventUuid}`);
        }
        if (actionType === 'create') {
            addParticipant(id as string, data)
                .then(createCallback)
                .catch((err) => setError(err));
        }
        else if (actionType === 'edit') {
            saveParticipant(id as string, data)
                .then(editCallback)
                .catch((err) => setError(err));
        }
    }

    const handleAbortClick = () => {
        if (actionType === 'edit') {
            eventUuid && navigate(`/event/${eventUuid}`);
        } else if (actionType === 'create') {
            navigate(`/`);
        }
    }

    return (<Form onSubmit={handleSubmit(onSubmit)}>
        {error && <Row>
            <Col>
                <Alert variant="danger">{error}</Alert>
            </Col>
        </Row>}
        {displayEntityTypeOptions && (
            <Form.Group as={Row} className="mb-3">
                <Col></Col>
                <Col>
                    <div>
                        <Form.Check inline label="Eraisik" type="radio" value="private" {...register('entityType')} />
                        <Form.Check inline label="Ettevõte" type="radio" value="legal" {...register('entityType')} />
                    </div>
                </Col>
            </Form.Group>
        )}
        {entityType === 'legal' && <LegalEntityParticipantForm register={register} />}
        {entityType === 'private' && <PrivateEntityParticipantForm register={register} />}
        <Form.Group as={Row} className="mb-3">
            <Form.Label column>Maksmisviis:</Form.Label>
            <Col>
                <PaymentTypeSelect paymentTypes={paymentTypes} register={register} />
            </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-3">
            <Form.Label column>Lisainfo:</Form.Label>
            <Col>
                <Form.Control as="textarea" {...register('details')}></Form.Control>
            </Col>
        </Form.Group>
        <Stack direction="horizontal" gap={3}>
            <Button variant="secondary" onClick={handleAbortClick}>
                Tagasi
            </Button>
            <Button variant="primary" type="submit">
                Salvesta
            </Button>
        </Stack>
    </Form>)
}

export default ParticipantForm;