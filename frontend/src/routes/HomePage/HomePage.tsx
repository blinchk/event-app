import {Col, Container, Row} from "react-bootstrap";
import homePageImage from '../../assets/pilt.jpg';

import './HomePage.scss';
import EventsWidget from "../../components/event/HomePageEvents/EventsWidget.tsx";

const HomePage = () => (
    <Container>
        <Container className="home-page-header-container">
            <Row>
                <Col>
                    <div className="home-page-text-wrapper">
                        <p className="home-page-text">Sed nec elit vestibulum, <strong>tincidunt orci</strong> et, sagittis ex. Vestibulum rutrum <strong>neque suscipit</strong> ante
                            mattis
                            maximus. Nulla non sapien <strong>viverra, lobortis lorem non</strong>, accumsan metus.</p>
                    </div>
                </Col>
                <Col>
                    <div className="home-page-image-wrapper">
                        <img className="home-page-image" src={homePageImage} alt=""/>
                    </div>
                </Col>
            </Row>
        </Container>
        <Container className="home-page-events-container">
            <Row>
                <Col>
                    <EventsWidget
                        title="Tulevased üritused"
                        type='future'
                    />
                </Col>
                <Col>
                    <EventsWidget
                        title="Toimunud üritused"
                        type='past'
                    />
                </Col>
            </Row>
        </Container>
    </Container>
);

export default HomePage;