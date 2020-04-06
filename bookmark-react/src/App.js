import React, { Fragment } from "react";
import { makeStyles } from "@material-ui/core/styles";
import "./App.css";
import Container from "@material-ui/core/Container";
import Navbar from "./components/nav/Navbar";
import CssBaseline from "@material-ui/core/CssBaseline";
import BookmarkListPage from "./pages/BookmarkListPage";
import AboutPage from "./pages/AboutPage";
import NotFoundPage from "./pages/NotFoundPage";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  toolbar: {
    paddingLeft: 0,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
  container: {
    paddingTop: theme.spacing(4),
    paddingBottom: theme.spacing(4),
  },
}));

function App() {
  const classes = useStyles();
  return (
    <Router>
      <Fragment>
        <CssBaseline />
        <div className={classes.root}>
          <Navbar />
          <Container maxWidth="lg" className={classes.container}>
            <Switch>
              <Route path="/" exact component={BookmarkListPage} />
              <Route path="/about" component={AboutPage} />
              <Route component={NotFoundPage} />
            </Switch>
          </Container>
        </div>
      </Fragment>
    </Router>
  );
}

export default App;
