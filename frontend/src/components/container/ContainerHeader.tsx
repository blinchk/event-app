import {Col, Row} from "react-bootstrap";
import containerHeaderImage from "../../assets/libled.jpg";

import './ContainerHeader.scss';

export interface ContainerHeaderProps {
    title: string
}

export const ContainerHeader = (props: ContainerHeaderProps) => (
    <div className="container-header">
        <Row>
            <Col xs={3}>
                <h2 className="container-header-text">{props.title}</h2>
            </Col>
            <Col>
                <img className="container-header-image" src={containerHeaderImage} alt=""/>
            </Col>
        </Row>
    </div>
)