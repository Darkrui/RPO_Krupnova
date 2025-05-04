import React from "react";
import { Navbar, Nav } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
<<<<<<< HEAD
import { faHome, faUser } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import Utils from "../utils/Utils";
import BackendService from "../services/BackendService";
=======
import { faHome } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
>>>>>>> a7346feb6c5203e7cc8e62c640906fbf9e1fcf49

class NavigationBarClass extends React.Component {
  constructor(props) {
    super(props);
    this.goHome = this.goHome.bind(this);
<<<<<<< HEAD
    this.logout = this.logout.bind(this);
  }
  logout() {
    BackendService.logout().then(() => {
      Utils.removeUser();
      this.goHome();
    });
=======
>>>>>>> a7346feb6c5203e7cc8e62c640906fbf9e1fcf49
  }

  goHome() {
    this.props.navigate("Home");
  }

  render() {
<<<<<<< HEAD
    let uname = Utils.getUserName();
=======
>>>>>>> a7346feb6c5203e7cc8e62c640906fbf9e1fcf49
    return (
      <Navbar bg="light" expand="lg">
        <Navbar.Brand>
          <FontAwesomeIcon icon={faHome} /> My RPO
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
<<<<<<< HEAD
            <Nav.Link as={Link} to="/home">
              Home
            </Nav.Link>
=======
            <Nav.Link href="/home">Home</Nav.Link>
>>>>>>> a7346feb6c5203e7cc8e62c640906fbf9e1fcf49
            <Nav.Link onClick={this.goHome}>Another Home</Nav.Link>
            <Nav.Link
              onClick={() => {
                this.props.navigate("home");
              }}
            >
              Yet Another Home
            </Nav.Link>
          </Nav>
<<<<<<< HEAD
          <Navbar.Text>{uname}</Navbar.Text>
          {uname && (
            <Nav.Link onClick={this.logout}>
              <FontAwesomeIcon icon={faUser} fixedWidth /> Выход
            </Nav.Link>
          )}
          {!uname && (
            <Nav.Link as={Link} to="/login">
              <FontAwesomeIcon icon={faUser} fixedWidth /> Вход
            </Nav.Link>
          )}
=======
>>>>>>> a7346feb6c5203e7cc8e62c640906fbf9e1fcf49
        </Navbar.Collapse>
      </Navbar>
    );
  }
}

const NavigationBar = (props) => {
  const navigate = useNavigate();

  return <NavigationBarClass navigate={navigate} {...props} />;
};

export default NavigationBar;
