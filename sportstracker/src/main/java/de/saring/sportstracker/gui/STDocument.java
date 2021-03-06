package de.saring.sportstracker.gui;

import java.util.List;

import de.saring.sportstracker.core.STException;
import de.saring.sportstracker.core.STOptions;
import de.saring.sportstracker.data.EntryList;
import de.saring.sportstracker.data.Exercise;
import de.saring.sportstracker.data.EntryFilter;
import de.saring.sportstracker.data.ExerciseList;
import de.saring.sportstracker.data.Note;
import de.saring.sportstracker.data.NoteList;
import de.saring.sportstracker.data.SportTypeList;
import de.saring.sportstracker.data.Weight;
import de.saring.sportstracker.data.WeightList;
import de.saring.util.data.IdObjectListChangeListener;
import de.saring.util.unitcalc.SpeedMode;

/**
 * This interface provides all document (MVC) related data and functionality of the
 * SportsTracker application.
 *
 * @author Stefan Saring
 */
public interface STDocument extends IdObjectListChangeListener {

    /** Command line parameter for a specific data directory (optional). */
    String PARAMETER_DATA_DIR = "--datadir=";

    // //// BEGIN: Getters and Setters for application data

    SportTypeList getSportTypeList();

    ExerciseList getExerciseList();

    NoteList getNoteList();

    WeightList getWeightList();

    STOptions getOptions();

    boolean isDirtyData();

    boolean isFilterEnabled();

    void setFilterEnabled(boolean filterEnabled);

    EntryFilter getCurrentFilter();

    void setCurrentFilter(EntryFilter currentFilter);

    String getDataDirectory();

    // //// END: Getters and Setters for application data

    /**
     * Evaluates the passed command line parameters.
     *
     * @param parameters list of specified command line parameters
     */
    void evaluateCommandLineParameters(List<String> parameters);

    /**
     * Loads the application options or creates default options when not available.
     */
    void loadOptions();

    /**
     * Stores the application options to file.
     */
    void storeOptions();

    /**
     * This method makes sure that the application data directory exists, otherwise it creates it.
     *
     * @throws STException thrown on problems creating application directory
     */
    void createApplicationDirectory() throws STException;

    /**
     * This method returns the list of exercises for display in the GUI.
     * If the filter is enabled, the returned list will contains just the
     * filtered exercises, otherwise it will contain all.
     *
     * @return list of Exercise objects
     */
    EntryList<Exercise> getFilterableExerciseList();

    /**
     * This method returns the list of notes for display in the GUI.
     * If the filter is enabled, the returned list will contains just the
     * filtered notes, otherwise it will contain all.
     *
     * @return list of Note objects
     */
    EntryList<Note> getFilterableNoteList();

    /**
     * This method returns the list of weights for display in the GUI.
     * If the filter is enabled, the returned list will contains just the
     * filtered weights, otherwise it will contain all.
     *
     * @return list of Weight objects
     */
    EntryList<Weight> getFilterableWeightList();

    /**
     * This method reads both the exercise and the sport-type list from the
     * storage (e.g. XML files). On read problems empty lists will be created.
     *
     * @throws STException thrown on read problems
     */
    void readApplicationData() throws STException;

    /**
     * This method stores both the exercise and the sport-type list in the storage
     * (e.g. XML files). On success the dirty data flag will be set to false.
     *
     * @throws STException thrown on store problems
     */
    void storeApplicationData() throws STException;

    /**
     * Checks all exercises for the existence of the attached exercise files
     * (if there is one).
     *
     * @return List of Exercise objects, where the specified file is missing
     */
    List<Exercise> checkExerciseFiles();

    /**
     * Register the specified IdObjectListChangeListener on all stored data lists
     * (for sport types, exercises, notes and weights).
     *
     * @param listener the IdObjectListChangeListener to register
     */
    void registerListChangeListener(IdObjectListChangeListener listener);

    /**
     * Returns the speed mode of the specified exercises which has to be used for displaying the speed for them (e.g.
     * for a bunch of selected exercises). When there are multiple exercises with multiple speed modes, then the
     * preferred speed mode will be used.
     *
     * @param exerciseIds IDs of exercises to check
     * @return speed mode to use for them
     */
    SpeedMode getSpeedModeForExercises(final int[] exerciseIds);
}
