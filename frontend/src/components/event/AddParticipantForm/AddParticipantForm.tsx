import {Button, Col, Form, Row} from "react-bootstrap";
import {SubmitHandler, useForm, UseFormRegister} from "react-hook-form";
import {
    LegalEntityParticipantRequest,
    PrivateEntityParticipantRequest,
    useParticipants
} from "../../../hooks/useParticipants.ts";
import {useNavigate, useParams} from "react-router-dom";
import LegalEntityParticipantForm from "./LegalEntityParticipantForm/LegalEntityParticipantForm.tsx";
import PrivateEntityParticipantForm from "./PrivateEntityParticipantForm/PrivateEntityParticipantForm.tsx";
import {PaymentTypeResponse} from "../../../hooks/usePaymentTypes.ts";

type ParticipantForm = PrivateEntityParticipantRequest & LegalEntityParticipantRequest & {
    type: 'legal' | 'private'
};

export interface ParticipantFormProps {
    register: UseFormRegister<ParticipantForm>
}

export interface PaymentTypeSelectProps {
    paymentTypes: PaymentTypeResponse[];
}

const PaymentTypeSelect = ({ paymentTypes, register }: PaymentTypeSelectProps & ParticipantFormProps) => (
    <Form.Select {...register('paymentTypeId')}>
        {paymentTypes.map((paymentType) => (
            <option key={paymentType.id} value={paymentType.id}>{paymentType.title}</option>
        ))}
    </Form.Select>
)

const AddParticipantForm = ({ paymentTypes }: PaymentTypeSelectProps) => {
    const {
        handleSubmit,
        register,
        watch,
        reset
    } = useForm<ParticipantForm>({
        defaultValues: {
            type: 'private'
        }
    });
    const { addLegalEntityParticipant, addPrivateEntityParticipant } = useParticipants();
    const navigate = useNavigate();

    const { id } = useParams<string>();
    const type = watch('type', 'private');
    const updateData = () => navigate('.', { replace: true });

    const onSubmit: SubmitHandler<ParticipantForm> = (data) => {
        const callback = () => {
            updateData();
            reset();
        }
        if (data.type === 'legal') {
            addLegalEntityParticipant(id as string, data).then(callback);
        } else if (data.type === 'private') {
            addPrivateEntityParticipant(id as string, data).then(callback);
        }

    }

    return (<Form onSubmit={handleSubmit(onSubmit)}>
        <Form.Group as={Row} className="mb-3">
            <Col></Col>
            <Col>
                <div>
                    <Form.Check inline label="Eraisik" type="radio" value="private" {...register('type')} />
                    <Form.Check inline label="Ettevõte" type="radio" value="legal" {...register('type')} />
                </div>
            </Col>
        </Form.Group>
        {type === 'legal' && <LegalEntityParticipantForm register={register} />}
        {type === 'private' && <PrivateEntityParticipantForm register={register} />}
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
        <Button variant="secondary">
            Tagasi
        </Button>
        <Button variant="primary" type="submit">
            Salvesta
        </Button>
    </Form>)
}

export default AddParticipantForm;